package edu.luc.etl.cs313.scala.akkaclickcounter
package ui

import java.io.{BufferedWriter, OutputStreamWriter, PrintWriter}
import java.net.{InetAddress, Socket}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ClientThread extends Thread {

  var socket: Socket=_
  var out: PrintWriter=null
  var stopped= false

  override def run(): Unit = {
    val serverAddr = InetAddress.getByName("172.16.203.121")
    socket = new Socket(serverAddr,4000)
    Future{
      while(!stopped){
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream)), true)
        out.println("a")
        Thread.sleep(500)
      }
      out.println("quit")
    }

  }


  def finisci(): Unit ={
    stopped=true
    Thread.sleep(600)
    socket.close()
  }

}
