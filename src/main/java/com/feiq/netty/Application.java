package com.feiq.netty;

import com.feiq.netty.common.ServerInfoCommon;
import com.feiq.netty.server.FeiQServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
  public static void main(String[] args) {
    log.info("Server Application Start...");
      try {
          new FeiQServer(ServerInfoCommon.FEIQ_SERVICE_DEFAULT_PORT).run();
      } catch (InterruptedException e) {
          log.error("FeiQ Server run error! ",e);
      }
  }
}
