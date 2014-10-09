/*
 * Copyright (c) 2013 DataTorrent, Inc. ALL Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datatorrent.lib.statistics;

import com.datatorrent.lib.testbench.CollectorTestSink;

import org.junit.Assert;
import org.junit.Test;

/**
 * Functional Test for {@link com.datatorrent.lib.statistics.WeightedMeanOperator}. <br>
 */
public class MeridianOperatorTest
{
  @Test
  public void testWeightedMean()
  {
    MedianOperator oper = new MedianOperator();
    CollectorTestSink<Object> sink = new CollectorTestSink<Object>();
    oper.median.setSink(sink);
    
    oper.setup(null);
    oper.beginWindow(0);
    oper.data.process(1.0);
    oper.data.process(7.0);
    oper.data.process(3.0);
    oper.data.process(9.0);
    oper.endWindow();
    
    Assert.assertEquals("Must be one tuple in sink", sink.collectedTuples.size(), 1);
    Assert.assertEquals("Median value", sink.collectedTuples.get(0), 5.0);
  }
}
