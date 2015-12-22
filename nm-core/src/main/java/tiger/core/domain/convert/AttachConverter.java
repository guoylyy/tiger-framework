package tiger.core.domain.convert;

import org.springframework.util.CollectionUtils;
import tiger.common.dal.dataobject.AttachDO;
import tiger.common.dal.enums.AttachTypeEnum;
import tiger.common.util.*;
import tiger.core.domain.AttachDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttachConverter {

    private AttachConverter() {

    }

    public static AttachDomain convert2Domain(AttachDO source) {
        if (null == source) {
            return null;
        }
        AttachDomain target = new AttachDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        if (StringUtil.isNotBlank(source.getMetaData())) {
            target.setMetaData(JsonUtil.fromJson(source.getMetaData(), HashMap.class));
        }
        if (null != source.getIsDel()) {
            target.setIsDel(ByteUtil.toBoolean(source.getIsDel()));
        }
        if (StringUtil.isNotBlank(source.getAttachType())) {
            target.setAttachType(AttachTypeEnum.getEnumByCode(source.getAttachType()));
        }
        return target;
    }

    /**
     * Convert2 domain.
     *
     * @param sourceList the source list
     * @return the list
     */
    public static List<AttachDomain> convert2Domain(List<AttachDO> sourceList) {
        List<AttachDomain> targetList = new ArrayList<>();
        for (AttachDO source : sourceList) {
            targetList.add(AttachConverter.convert2Domain(source));
        }
        return targetList;
    }

    /**
     * Convert2 do.
     *
     * @param source the source
     * @return the customer do
     */
    public static AttachDO convert2DO(AttachDomain source) {
        AttachDO target = new AttachDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        if (!CollectionUtils.isEmpty(source.getMetaData())) {
            target.setMetaData(JsonConverterUtil.serialize(source.getMetaData()));
        }
        if (null != source.getIsDel()) {
            target.setIsDel(ByteUtil.booleanToByte(source.getIsDel()));
        }
        if (null != source.getAttachType()) {
            target.setAttachType(source.getAttachType().getCode());
        }
        return target;
    }

}
