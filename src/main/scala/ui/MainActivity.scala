package edu.luc.etl.cs313.scala.akkaclickcounter
package ui

import akka.actor.{ActorRef, ActorSystem, Props}
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import model._
import BoundedCounterActor._
import ui.ClientThread


/**
 * The main Android activity, which provides the required lifecycle methods.
 * By mixing in the reactive view behaviors, this class serves as the Adapter
 * in the Model-View-Adapter pattern. It connects the Android GUI view with the
 * reactive model.
 */
class MainActivity extends Activity with TypedActivity {

  private def TAG = "clickcounter-android-akka"

  var thread: ClientThread= _

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    Log.i(TAG, "onCreate")
    setContentView(R.layout.main)
    thread = new ClientThread()
    thread.start()
  }


  override def onBackPressed(): Unit = {
    this.thread.finisci()
    super.onBackPressed()
  }




}
