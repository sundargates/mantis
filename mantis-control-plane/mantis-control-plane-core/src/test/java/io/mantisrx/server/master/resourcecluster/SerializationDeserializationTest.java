/*
 * Copyright 2022 Netflix, Inc.
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
package io.mantisrx.server.master.resourcecluster;

import static org.junit.Assert.assertEquals;

import io.mantisrx.common.Ack;
import io.mantisrx.common.JsonSerializer;
import org.junit.Test;

public class SerializationDeserializationTest {
    private final JsonSerializer serializer = new JsonSerializer();

    @Test
    public void testTaskExecutorRegistrationDeserialization() throws Exception {
        String str = "{\n"
                + "    \"taskExecutorID\":\n"
                + "    {\n"
                + "        \"resourceId\": \"25400d92-96ed-40b9-9843-a6e7e248db52\"\n"
                + "    },\n"
                + "    \"clusterID\":\n"
                + "    {\n"
                + "        \"resourceID\": \"mantistaskexecutor\"\n"
                + "    },\n"
                + "    \"taskExecutorAddress\": \"akka.tcp://flink@100.118.114.30:5050/user/rpc/worker_0\",\n"
                + "    \"hostname\": \"localhost\",\n"
                + "    \"workerPorts\":\n"
                + "    {\n"
                + "        \"metricsPort\": 5051,\n"
                + "        \"debugPort\": 5052,\n"
                + "        \"consolePort\": 5053,\n"
                + "        \"customPort\": 5054,\n"
                + "        \"ports\":\n"
                + "        [\n"
                + "            5055,\n"
                + "            5051,\n"
                + "            5052,\n"
                + "            5053,\n"
                + "            5054\n"
                + "        ],\n"
                + "        \"sinkPort\": 5055,\n"
                + "        \"numberOfPorts\": 5,\n"
                + "        \"valid\": true\n"
                + "    },\n"
                + "    \"machineDefinition\":\n"
                + "    {\n"
                + "        \"cpuCores\": 4.0,\n"
                + "        \"memoryMB\": 17179869184,\n"
                + "        \"networkMbps\": 128.0,\n"
                + "        \"diskMB\": 88969576448,\n"
                + "        \"numPorts\": 5\n"
                + "    }\n"
                + "}";

        final TaskExecutorRegistration registration =
                serializer.fromJSON(str, TaskExecutorRegistration.class);
        final TaskExecutorRegistration deserialized =
                serializer.fromJSON(serializer.toJson(registration), TaskExecutorRegistration.class);
        assertEquals(registration, deserialized);
    }

    @Test
    public void testHeartbeat() throws Exception {
        TaskExecutorHeartbeat heartbeat =
                new TaskExecutorHeartbeat(
                        TaskExecutorID.generate(),
                        ClusterID.of("cluster"),
                        TaskExecutorReport.available());
        String encoded = serializer.toJson(heartbeat);

        assertEquals(serializer.fromJSON(encoded, TaskExecutorHeartbeat.class), heartbeat);
    }

    @Test
    public void testAckInstance() throws Exception {
        Ack ack = Ack.getInstance();
        String serialized = serializer.toJson(ack);
        Ack actual = serializer.fromJSON(serialized, Ack.class);
        assertEquals(ack, actual);
    }
}