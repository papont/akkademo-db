package com.akkademo

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.akkademo.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, Matchers, FunSpecLike}

class LasMesActorSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {

  implicit val system = ActorSystem()

  describe("lastMesActor") {
    describe("given SetRequest") {

      it("should place key/value into map") {
        val actorRef = TestActorRef(new LastMesActor)
        actorRef ! SetRequest("key", "value")

        val lastMesActor = actorRef.underlyingActor
        lastMesActor.map.get("key") should equal(Some("value"))
      }

      it("should place 1 message into map") {
        val actorRef = TestActorRef(new LastMesActor)
        actorRef ! SetRequest("key", "value")
        actorRef ! SetRequest("keyLast", "valueLast")

        val lastMesActor = actorRef.underlyingActor
        lastMesActor.map.get("keyLast") should equal(Some("valueLast"))

        lastMesActor.map.last._2 should equal("valueLast")

        lastMesActor.map.size should equal(1)
      }

    }
  }

}
