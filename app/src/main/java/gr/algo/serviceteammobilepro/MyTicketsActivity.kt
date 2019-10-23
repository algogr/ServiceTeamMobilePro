package gr.algo.serviceteammobilepro

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_my_tickets.*
import org.json.JSONArray

import org.joda.time.format.DateTimeFormat



class MyTicketsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tickets)
        val techid: String="1"
        var ticketList: MutableList<Ticket> = mutableListOf()
        val queue= Volley.newRequestQueue(this)
        val url="http://localhost:8080/tickets?$techid"
        val ticketAdapter=TicketAdapter(this@MyTicketsActivity,ticketList)
        var jsonArrReq= JsonArrayRequest(Request.Method.GET,url,null,
                object : Response.Listener<JSONArray>{

                    override fun onResponse(p0: JSONArray) {


                        for (i in 0 until p0.length())
                        {
                            val im=p0.getJSONObject(i).getString("im")
                            val appdatestr = p0.getJSONObject(i).getString("appointment")
                            val formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss")
                            val appointment=formatter.parseDateTime(appdatestr)
                            val title= p0.getJSONObject(i).getString("title")
                            val company: String=p0.getJSONObject(i).getString("company")
                            val subsName: String=p0.getJSONObject(i).getString("subsName")
                            val location: String?=p0.getJSONObject(i).getString("location")
                            val city: String?=p0.getJSONObject(i).getString("city")
                            val address: String?=p0.getJSONObject(i).getString("address")
                            val phone1:String?=p0.getJSONObject(i).getString("phone1")
                            val phone2:String?=p0.getJSONObject(i).getString("phone2")
                            val loopnumber:String?=p0.getJSONObject(i).getString("loopnumber")
                            val description:String?=p0.getJSONObject(i).getString("description")


                            val ticket=Ticket(im)
                            ticket.appointment=appointment
                            ticket.title=title
                            ticket.commpany=company
                            ticket.subsName=subsName
                            ticket.location=location
                            ticket.city=city
                            ticket.address=address
                            ticket.phone1=phone1
                            ticket.phone2=phone2
                            ticket.loopnumber=loopnumber
                            ticket.description=description

                            ticketList.add(i,ticket)




                        }


                        ticketListView.adapter=ticketAdapter
                    }

                },object : Response.ErrorListener{
            override fun onErrorResponse(p0: VolleyError?) {

                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        queue.add(jsonArrReq)


    }


    inner class TicketAdapter : BaseAdapter {

        private  var ticketList : MutableList<Ticket>?
        private  var context: Context? =null
        private  val mInflator: LayoutInflater
        private var hashMapTexts:  HashMap<String,String>



        constructor(context: Context, ticketList: MutableList<Ticket>) : super(){
            this.ticketList= ticketList
            this.context=context
            this.hashMapTexts= HashMap()
            this.mInflator= LayoutInflater.from(context)
            for ( ticket in ticketList) {

                this.hashMapTexts.put(ticket.im,"")

            }
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val holder: MyTicketsActivity.ViewHolder
            val view: View

            if (convertView==null){
                view= layoutInflater.inflate(R.layout.ticket_item_layout,parent,false)
                holder=ViewHolder(view)
                view.tag=holder
                Log.i("JSA","Set log for Viewholder,position: "+position)
            }
            else {

                view = convertView
                holder = view.tag as ViewHolder
            }
            holder.position=position
            holder.textView.text=ticketList!![position].im
            return view
        }

        override fun getItem(position: Int): Any {
            return ticketList!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return ticketList!!.size
        }

    }


    private class ViewHolder(view: View?){
        var textView:TextView
        var position:Int

        init{
            this.textView=view?.findViewById<TextView>(R.id.textView) as TextView
            this.position=-1
        }
    }

}
