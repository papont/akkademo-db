package com.akkademo

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.akkademo.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, Matchers, FunSpecLike}

/**
  * Created by admin on 09.08.16.
  */
class AkkademoDbSpec  extends FunSpecLike with Matchers with BeforeAndAfterEach {

  implicit val system = ActorSystem()

  describe("akkademoDb") {
    describe("given SetRequest") {

      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademoDb)
        actorRef ! SetRequest("key", "value")

        val akkademoDb = actorRef.underlyingActor
        akkademoDb.map.get("key") should equal(Some("value"))
      }
    }
  }

}
