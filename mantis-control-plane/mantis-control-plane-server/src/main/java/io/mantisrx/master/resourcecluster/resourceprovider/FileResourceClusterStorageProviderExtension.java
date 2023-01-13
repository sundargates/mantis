/*
 * Copyright 2023 Netflix, Inc.
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

package io.mantisrx.master.resourcecluster.resourceprovider;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import io.mantisrx.server.core.config.MantisExtension;
import java.io.File;

@SuppressWarnings("unused")
public class FileResourceClusterStorageProviderExtension implements MantisExtension<ResourceClusterStorageProvider> {
    @Override
    public SimpleFileResourceClusterStorageProvider createObject(Config config, ActorSystem actorSystem) {
        String storageDirectory = config.getString("mantis.resourceCluster.store.directory");
        return new SimpleFileResourceClusterStorageProvider(actorSystem, new File(storageDirectory));
    }
}
