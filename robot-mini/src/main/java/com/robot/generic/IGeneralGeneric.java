package com.robot.generic;

import java.util.Map;

public interface IGeneralGeneric{
    Map<String, Object> getFieldNamesAndValues(final Object obj, boolean publicOnly) throws IllegalArgumentException,IllegalAccessException;
}
