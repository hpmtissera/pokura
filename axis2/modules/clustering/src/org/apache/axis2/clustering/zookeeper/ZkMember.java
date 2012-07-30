/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.axis2.clustering.zookeeper;

import java.io.Serializable;
import java.util.UUID;

public interface ZkMember extends Serializable{

    public byte[] getZkHost();

    public String getZkHostName();

    public UUID getZkNodeID();

    public int getPort();

    public byte[] getDomain();

    public boolean isAlive();

    public byte[] getPayLoad();

    public void setZkHost(byte[] zkHost);

    public void setZkHostName(String zkHostName);

    public void setZkNodeId(UUID zkNodeId);

    public void setPort(int port);

    public void setDomain(byte[] domain);

    public void setPayLoad(byte[] payLoad);

    public boolean equals(Object obj);

    public int hashCode();

}
