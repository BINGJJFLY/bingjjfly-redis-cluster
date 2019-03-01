package com.wjz.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterTest {

	private Set<HostAndPort> hps;
	private JedisCluster cluster;

	@Before
	public void init() {
		final HostAndPort h136_p7000 = new HostAndPort("192.168.188.136", 7000);
		final HostAndPort h136_p7001 = new HostAndPort("192.168.188.136", 7001);
		final HostAndPort h136_p7002 = new HostAndPort("192.168.188.136", 7002);
		final HostAndPort h137_p7000 = new HostAndPort("192.168.188.137", 7000);
		final HostAndPort h137_p7001 = new HostAndPort("192.168.188.137", 7001);
		final HostAndPort h137_p7002 = new HostAndPort("192.168.188.137", 7002);

		hps = new HashSet<HostAndPort>(6);
		hps.add(h136_p7000);
		hps.add(h136_p7001);
		hps.add(h136_p7002);
		hps.add(h137_p7000);
		hps.add(h137_p7001);
		hps.add(h137_p7002);

		cluster = new JedisCluster(hps);
	}

	@Test
	public void insert() {
		Long l = cluster.lpush("days", "Friday", "Monday");
		Assert.assertEquals((Long) 2L, l);
	}

}
