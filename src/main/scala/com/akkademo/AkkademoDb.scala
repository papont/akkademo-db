package com.akkademo

import akka.actor.Actor
import akka.event.Logging
import com.akkademo.messages.SetRequest

import scala.collection.mutable.HashMap


class AkkademoDb extends Actor {

  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) => {
      log.info("receive setRequest - key: {} value: {}", key, value)
      map.put(key, value)
    }
    case o => log.info("received unknown message: {}", o)
  }
}
