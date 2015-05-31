/*
 Copyright 2015 Coursera Inc.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package org.coursera.courier.generator

import org.coursera.enums.Fruits
import org.coursera.records.test.Empty
import org.coursera.records.test.EmptyArray
import org.coursera.records.test.EmptyMap
import org.coursera.typerefs.UnionTyperef
import org.junit.BeforeClass
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit

object TyperefGeneratorTest extends SchemaFixtures with GeneratorTest {

  @BeforeClass
  def setup(): Unit = {
    generateTestSchemas(Seq(
      Enums.Fruits,
      Records.Empty,
      Typerefs.IntTyperef,
      Typerefs.ArrayTyperef,
      Typerefs.MapTyperef,
      Typerefs.UnionTyperef,
      Typerefs.EnumTyperef))
  }
}

class TyperefGeneratorTest extends GeneratorTest with SchemaFixtures with AssertionsForJUnit {

  @Test
  def testTyperefs(): Unit = {
    // TODO: We're mainly interested in making sure the referenced type exist, but we should test them further
    UnionTyperef.IntMember(1).data()
    EmptyArray(Empty()).data()
    EmptyMap("a" -> Empty()).data()
    Fruits.BANANA.toString
  }
}
