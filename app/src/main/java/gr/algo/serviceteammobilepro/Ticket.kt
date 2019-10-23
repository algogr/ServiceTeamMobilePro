package gr.algo.serviceteammobilepro

import org.joda.time.DateTime
import java.time.LocalDateTime

data class Ticket(
        val im:String,
        var appointment: DateTime ?=null,
        var title: String?=null,
        var commpany: String="",
        var subsName: String="",
        var location: String?=null,
        var city: String?=null,
        var address: String?=null,
        var phone1:String?=null,
        var phone2:String?=null,
        var loopnumber:String?=null,
        var description:String?=null
)
