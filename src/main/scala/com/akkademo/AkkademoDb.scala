package com.akkademo

import akka.actor.Actor
import akka.actor.Actor.Receive
import akka.event.Logging
import com.akkademo.messages.SetRequest

import scala.collection.mutable.HashMap

/**
 * Created by user on 09.08.2016.
 */
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
