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

package io.mantisrx.server.worker;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.apache.flink.util.SimpleUserCodeClassLoader;
import org.apache.flink.util.UserCodeClassLoader;

@RequiredArgsConstructor(staticName = "of")
public class SimpleClassLoaderHandle implements ClassLoaderHandle {
    private final ClassLoader classLoader;

    @Override
    public UserCodeClassLoader getOrResolveClassLoader(Collection<URI> requiredJarFiles, Collection<URL> requiredClasspaths) throws IOException {
        return SimpleUserCodeClassLoader.create(classLoader);
    }

    @Override
    public void close() throws IOException {
    }
}