package uuboy.scy.common.api;

import org.apache.http.HttpEntity;
import java.util.List;

/**
 * Project: uuboycommon
 * Package: uuboy.scy.common.api
 * ApiTransformation.java
 * Description:
 * User: uuboyscy
 * Created Date: 1/26/22
 * Version: 0.0.1
 */

public interface ApiTransformation {
    List<Object> trans(HttpEntity httpEntity);
    Object transToObj(HttpEntity httpEntity);
}
