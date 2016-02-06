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

import org.coursera.courier.templates.ScalaEnumerationTemplate
import org.coursera.enums.EnumProperties
import org.coursera.enums.Fruits
import org.junit.Test

class EnumGeneratorTest extends GeneratorTest with SchemaFixtures {

  @Test
  def testSimpleEnum(): Unit = {
    assert(Fruits.fromString("APPLE") === Fruits.APPLE)
    assert(Fruits.fromString("BWAHH") === Fruits.$UNKNOWN)
    assert(Fruits.values.contains(Fruits.BANANA) === true)
  }

  @Test
  def testCompare(): Unit = {
    assert(EnumProperties.APPLE.compare(EnumProperties.ORANGE) < 0)
  }

  @Test
  def testEnumProperties_symbol(): Unit = {
    val maybeColor = EnumProperties.BANANA.property("color").collect {
      case color: String => color
    }
    assert(maybeColor === Some("yellow"))

    val orange = EnumProperties.fromString("ORANGE")
    assert(Set(EnumProperties.BANANA, EnumProperties.ORANGE).contains(orange))
  }

  @Test
  def testEnumProperties_ref(): Unit = {
    def toColor(entry: EnumProperties.EnumProperties): String = {
      entry.property("color").collect {
        case color: String => color
      }.getOrElse(throw new RuntimeException())
    }

    assert(toColor(EnumProperties.BANANA) === "yellow")
  }
}