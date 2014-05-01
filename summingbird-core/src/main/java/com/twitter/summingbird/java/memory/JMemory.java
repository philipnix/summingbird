package com.twitter.summingbird.java.memory;

import scala.Function1;
import scala.Option;
import scala.collection.TraversableOnce;
import scala.collection.mutable.Map;

import com.twitter.summingbird.java.JProducer;
import com.twitter.summingbird.java.Service;
import com.twitter.summingbird.java.Sink;
import com.twitter.summingbird.java.Source;
import com.twitter.summingbird.java.Store;
import com.twitter.summingbird.java.impl.JProducerImpl;
import com.twitter.summingbird.memory.Memory;

public class JMemory {

  static <T> JProducer<Memory, T> source(TraversableOnce<T> source) {
    return JProducerImpl.source(new Source<Memory, TraversableOnce<T>, T>(source));
  }

//  type Store[K, V] = MutableMap[K, V]
  static <K,V> Store<Memory, Map<K, V>, K, V> store(Map<K, V> store) {
    return new Store<Memory, Map<K, V>, K, V>(store);
  }

//      type Sink[-T] = (T => Unit)
  static <T> Sink<Memory, Function1<T, Void>, T> sink(Function1<T, Void> sink) {
    return new Sink<Memory, Function1<T, Void>, T>(sink);
  }

//      type Service[-K, +V] = (K => Option[V])
  static <K,V> Service<Memory, Function1<K, Option<V>>, K, V> service(Function1<K, Option<V>> service) {
    return new Service<Memory, Function1<K, Option<V>>, K, V>(service);
  }

}
