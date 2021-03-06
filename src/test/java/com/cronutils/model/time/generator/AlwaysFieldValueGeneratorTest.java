package com.cronutils.model.time.generator;

import com.cronutils.model.field.constraint.FieldConstraintsBuilder;
import com.cronutils.model.field.expression.Always;
import com.cronutils.model.field.expression.FieldExpression;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
/*
 * Copyright 2015 jmrozanec
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class AlwaysFieldValueGeneratorTest {
    private AlwaysFieldValueGenerator fieldValueGenerator;

    @Before
    public void setUp(){
        fieldValueGenerator = new AlwaysFieldValueGenerator(new Always(FieldConstraintsBuilder.instance().createConstraintsInstance()));
    }

    @Test
    public void testGenerateNextValue() throws Exception {
        for(int j=0; j<10; j++){
            assertEquals(j+1, fieldValueGenerator.generateNextValue(j));
        }
    }

    @Test
    public void testGeneratePreviousValue() throws Exception {
        for(int j=0; j<10; j++){
            assertEquals(j-1, fieldValueGenerator.generatePreviousValue(j));
        }
    }

    @Test
    public void testGenerateCandidatesNotIncludingIntervalExtremes() throws Exception {
        List<Integer> values = fieldValueGenerator.generateCandidatesNotIncludingIntervalExtremes(0, 10);
        for(int j = 1; j<10; j++){
            assertTrue(values.contains(j));
        }
        assertEquals(9, values.size());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(fieldValueGenerator.isMatch((int)(10*Math.random())));
    }

    @Test
    public void testMatchesFieldExpressionClass() throws Exception {
        assertTrue(fieldValueGenerator.matchesFieldExpressionClass(mock(Always.class)));
        assertFalse(fieldValueGenerator.matchesFieldExpressionClass(mock(FieldExpression.class)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNotMatchesAlways() throws Exception {
        new AlwaysFieldValueGenerator(mock(FieldExpression.class));
    }
}