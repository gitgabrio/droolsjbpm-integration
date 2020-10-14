/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.pmml.trusty.container.tester;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.kie.api.builder.ReleaseId;
import org.kie.pmml.api.PMMLRuntimeFactory;
import org.kie.pmml.api.runtime.PMMLRuntime;
import org.kie.pmml.evaluator.assembler.factories.PMMLRuntimeFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPMMLTest {

    public static final String GROUP_ID = "org.drools";
    public static final String ARTIFACT_ID = "kie-pmml-trusty-integration-tests";
    public static final String VERSION = "@kjar.version@";
    public static final String GAV = String.format("%s:%s:%s", GROUP_ID, ARTIFACT_ID, VERSION);

    private static final Logger logger = LoggerFactory.getLogger(AbstractPMMLTest.class);

    private static final String KIEBASE_NAME = "KBaseDefault";
    private static final PMMLRuntimeFactory PMML_RUNTIME_FACTORY = new PMMLRuntimeFactoryImpl();

    protected AbstractPMMLExecutor abstractPMMLExecutor;
    protected PMMLRuntime pmmlRuntime;

    public AbstractPMMLTest(PMMLRuntime pmmlRuntime) {
        this.pmmlRuntime = pmmlRuntime;
    }

    protected static PMMLRuntime getPMMLRuntime(String modelName, String pmmlFile) {
        return  PMML_RUNTIME_FACTORY.getPMMLRuntimeFromKieContainerByKieBase(KIEBASE_NAME, pmmlFile, GAV);
    }

    @Test
    public void test() throws Exception {
        abstractPMMLExecutor.test(pmmlRuntime);
    }
}
