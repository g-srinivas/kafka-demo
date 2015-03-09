package example.producer

import java.util._
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import kafka.producer.Partitioner
import kafka.producer.ProducerConfig
import kafka.utils.VerifiableProperties
import scala.util.Random

 object Main extends  Application {

   println("hello world")
    val events: Long = 567
    val rnd = new Random()

    val  props = new Properties()
    props.put("metadata.broker.list", "localhost:9093,localhost:9094")
    props.put("serializer.class", "kafka.serializer.StringEncoder")
    props.put("partitioner.class", "example.producer.SimplePartitioner")
    props.put("request.required.acks", "1")

    val config = new ProducerConfig(props)
    val producer = new Producer[String,String](config)

      val runtime = new Date().getTime()
      val ip = "192.168.2." + rnd.nextInt(255)
      val msg = runtime + ",www.google.com," + ip
   val data = new KeyedMessage[String, String]("page_visits", ip, msg);
      producer.send(data)

    producer.close

}



class SimplePartitioner(props: VerifiableProperties) extends Partitioner{
  /*override def  partition(key: Object,  a_numPartitions: Int) {
    var partition = 0
    val  stringKey = key.toString
    val offset = stringKey.lastIndexOf('.')
    if (offset > 0) {
      partition = Integer.parseInt( stringKey.substring(offset+1)) % a_numPartitions;
    }
    return partition;
  }*/
  override def partition(key: Any, numPartitions: Int): Int = {
    var partition = 0
    val  stringKey = key.toString
    val offset = stringKey.lastIndexOf('.')
    if (offset > 0) {
      partition = Integer.parseInt( stringKey.substring(offset+1)) % numPartitions
    }
    return partition
  }
}
