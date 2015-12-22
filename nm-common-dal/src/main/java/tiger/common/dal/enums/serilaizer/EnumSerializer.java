/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums.serilaizer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import tiger.common.dal.enums.BaseEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:35 PM yiliang.gyl Exp $
 */
public class EnumSerializer extends JsonSerializer<BaseEnum> {

    @Override
    public void serialize(BaseEnum value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        map.put("code",value.getCode());
        map.put("value",value.getValue());
        jgen.writeObject(map);
    }

}
