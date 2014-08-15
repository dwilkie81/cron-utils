package com.cron.utils.parser;

import com.cron.utils.CronFieldName;
import com.cron.utils.model.FieldDefinition;
import com.cron.utils.parser.field.FieldConstraintsBuilder;
import org.apache.commons.lang3.Validate;

/**
 * Provides means to define cron field definitions
 */
class FieldDefinitionBuilder {
    protected CronDefinitionBuilder parserBuilder;
    protected final CronFieldName fieldName;
    protected FieldConstraintsBuilder constraints;

    /**
     * Constructor
     * @param parserBuilder - ParserDefinitionBuilder instance -
     *                      if null, a NullPointerException will be raised
     * @param fieldName - CronFieldName instance -
     *                  if null, a NullPointerException will be raised
     */
    public FieldDefinitionBuilder(CronDefinitionBuilder parserBuilder, CronFieldName fieldName){
        this.parserBuilder = Validate.notNull(parserBuilder, "ParserBuilder must not be null");
        this.fieldName = Validate.notNull(fieldName, "CronFieldName must not be null");
        this.constraints = FieldConstraintsBuilder.instance().forField(fieldName);
    }

    /**
     * Provides means to define int values mappings between equivalent values.
     * As a convention, higher values are mapped into lower ones
     * @param source - higher value
     * @param dest - lower value with equivalent meaning to source
     * @return this instance
     */
    public FieldDefinitionBuilder withIntMapping(int source, int dest){
        constraints.withIntValueMapping(source, dest);
        return this;
    }

    /**
     * Registers CronField in ParserDefinitionBuilder and returns its instance
     * @return ParserDefinitionBuilder instance obtained from constructor
     */
    public CronDefinitionBuilder and(){
        parserBuilder.register(new FieldDefinition(fieldName, constraints.createConstraintsInstance()));
        return parserBuilder;
    }
}