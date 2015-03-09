import java.util._

import org.apache.kafka.clients.producer.{ProducerRecord, KafkaProducer,  ProducerConfig}
import scala.util.Random

val events: Long = 567
val rnd = new Random()

val  props = new Properties()
props.put("metadata.broker.list", "broker1:9093,broker2:9094 ")
props.put("serializer.class", "kafka.serializer.StringEncoder")
props.put("partitioner.class", "example.producer.SimplePartitioner")
props.put("request.required.acks", "1")

//val config = new ProducerConfig(props)

val producer: KafkaProducer[String, String] = new KafkaProducer(props)

val runtime = new Date().getTime();
val ip = "192.168.2." + rnd.nextInt(255);
val msg = runtime + ",www.example.com," + ip;
val data = new ProducerRecord[String, String]("page_visits", ip, msg);
producer.send(data)

producer.close();