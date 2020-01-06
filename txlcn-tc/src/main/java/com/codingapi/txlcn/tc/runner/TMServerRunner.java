package com.codingapi.txlcn.tc.runner;

import com.codingapi.txlcn.protocol.client.PeerClientHandle;
import com.codingapi.txlcn.tc.config.TxConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class TMServerRunner {

    private TxConfig txConfig;

    private PeerClientHandle peerClientHandle;

    public void init(){
        peerClientHandle.setConfig(txConfig.getProtocol());
        String applicationName = txConfig.getApplicationName();
        List<String> list = txConfig.getTms();
        log.info("TM servers:{}",list);
        if(list!=null) {
            for (String item : list) {
                Optional<InetSocketAddress> optional = Optional.of(txConfig.addressFormat(item));
                optional.ifPresent(address -> peerClientHandle.connectTo(applicationName, address.getHostString(), address.getPort()));
            }
        }
    }
}