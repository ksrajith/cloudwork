package com.cloud.data;

import com.cloud.pojo.ServiceDataObj;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.TestDataReader;
import io.github.sskorol.data.XlsxReader;
import one.util.streamex.StreamEx;

import java.util.stream.Collectors;


/**
 * Use for the excel data manage
 */
public class DataManager {
    @DataSupplier(runInParallel = true)
    public ServiceDataObj getServiceData(String testCase) {
        return TestDataReader.use(XlsxReader.class)
                .withTarget(ServiceDataObj.class)
                .withSource("excel/cloudtestdata.xlsx")
                .read()
                .filter(createUserDataObj -> createUserDataObj.getTestCaseName().equalsIgnoreCase(testCase)).collect(Collectors.toList()).get(0);
    }
}