package com.akkademo

import akka.actor.Actor
import akka.event.Logging
import com.akkademo.messages.SetRequest

import scala.collection.mutable.HashMap


class LastMesActor extends Actor {

  var map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) => {
      log.info("receive setRequest - key: {} value: {}", key, value)

      if (!map.isEmpty) map.clear()

      map.put(key, value)
    }
    case o => log.info("received unknown message: {}", o)
  }
}
