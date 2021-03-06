package com.cronutils.model.field.expression;

/*
 * Copyright 2014 jmrozanec
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

import com.cronutils.model.field.constraint.FieldConstraints;
import com.cronutils.model.field.value.IntegerFieldValue;

/**
 * Represents every x time on a cron field.
 */
public class Every extends FieldExpression {
    private IntegerFieldValue time;

    public Every(FieldConstraints constraints, IntegerFieldValue time) {
        super(constraints);
        if (time == null) {
            time = new IntegerFieldValue(1);
        }
        this.time = time;
    }

    private Every(Every every){
        this(every.constraints, every.time);
    }

    public IntegerFieldValue getTime() {
        return time;
    }

    @Override
    public String asString() {
        if(time.getValue()==1){
            return "";
        }
        return String.format("/%s", getTime());
    }
}
