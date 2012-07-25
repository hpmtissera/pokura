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

import java.util.List;

import org.apache.axis2.clustering.tribes.MembershipManager;

public class ZooKeeperCommandSubscriber {
	private ZooKeeperMembershipManager membershipManager;
	private Integer initialId;
	public static long startTime;
	public static long eventCount;
		
	public ZooKeeperCommandSubscriber(ZooKeeperMembershipManager membershipManager) {
		this.membershipManager = membershipManager;
	}

	/**
	 * Set Zookeeper command listener
	 */
	public void startRecieve() {
		System.out.println("start receive called");
		String domainName = new String(membershipManager.getDomain());
		String commandPath = "/" + domainName
				+ ZooKeeperConstants.COMMANDS_BASE_NAME;
		initialId = generateCurrentId(commandPath);
				
		ZooKeeperUtils.getZookeeper().subscribeChildChanges(commandPath,
				new ZooKeeperCommandListener(initialId));
		System.out.println("start receive finished");
		
	}
	
	public void timoutCommandProcess()
	{
		String domainName = new String(membershipManager.getDomain());
		String commandPath = "/" + domainName
				+ ZooKeeperConstants.COMMANDS_BASE_NAME;
		
		 List<String> currentChilds = ZooKeeperUtils.getZookeeper().getChildren(commandPath);
		
		for (int i = ZooKeeperCommandListener.currentId; i < currentChilds.size(); i++) {
			System.out.println(currentChilds.get(i) + " after timeout processing...");
		}
	}

	public void stopRecive() {
		// TODO this method should be able to remove the chlidlistners from the
		// given path
	}

	/**
	 * Generated the sequence number of the command
	 * 
	 * @param commandPath
	 *            - pass the path of the command objects to process
	 * @return return the number of command objects in the given path
	 */
	private Integer generateCurrentId(String commandPath) {
		// TODO size cannot do because later old commands have to delete
		System.out.println(commandPath);
		if(ZooKeeperUtils.getZookeeper().exists(commandPath))
		{
		return ZooKeeperUtils.getZookeeper().getChildren(commandPath).size();
		}
		else
		{
			return 0;
		}
	}

}