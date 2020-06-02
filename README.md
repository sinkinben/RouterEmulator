# Router Emulator
Author: sinkinben
 
## Introduction 
Mutiple processes to emulate routers and hosts. The host will send 10 data messages to another host every 10 seconds. 
 
## How to Run 
* **[IMPORTANT]** Set project encoding to utf8, otherwise Chinese characters can't be displayed properly.
* First, run `entity/router/Router.java`, only one router is permitted.  
* Second, run `entity/host/Host.java`, max=8 hosts is supported.(To support more hosts, you need to add more IPs in Global.ipPool[])  
