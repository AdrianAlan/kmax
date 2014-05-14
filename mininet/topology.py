#!/usr/bin/python

from mininet.topo import Topo
from mininet.net import Mininet
from mininet.node import CPULimitedHost
from mininet.link import TCLink
from mininet.util import dumpNodeConnections
from mininet.util import dumpNetConnections
from mininet.log import setLogLevel
from mininet.cli import CLI
from mininet.node import RemoteController


'''
This topology is for k-shortest path project
'''

class MyTopo(Topo):

    def __init__(self, **opts):
        Topo.__init__(self, **opts)

        switches = []
        hosts = []

        for i in range(6):
            switches.append(self.addSwitch('s'+str(i + 1)))
       
        for i in range(3):
            hosts.append(self.addHost('h'+str(i+1)))

        self.addLink(switches[0], hosts[0], bw=1000, max_queue_size=1000) 
        self.addLink(switches[0], hosts[1], bw=1000, max_queue_size=1000)

        self.addLink(switches[0], switches[1], bw=10, max_queue_size=1000)
        self.addLink(switches[1], switches[5], bw=10, max_queue_size=1000)

        self.addLink(switches[0], switches[2], bw=1, max_queue_size=1000)
        self.addLink(switches[2], switches[5], bw=1, max_queue_size=1000)

        self.addLink(switches[0], switches[3], bw=1000, max_queue_size=1000)
        self.addLink(switches[3], switches[4], bw=1000, max_queue_size=1000)
        self.addLink(switches[4], switches[5], bw=1000, max_queue_size=1000)

        self.addLink(switches[5], hosts[2], bw=1000, max_queue_size=1000)


topos = { 'defualt': ( lambda: MyTopo() ) }  # this makes it possible to run the mininet with the parameter "--topo mytopo"


class FLController (RemoteController):

    def __init__ (self,name):
        RemoteController.__init__(self,'FLController','127.0.0.1',6633)

controllers={'flcontroller': FLController}   # this makes it possible to run mininet with the parameter "--controller flcontroller"

def perfTest():
    '''
    This function runs only when executing the script with python: "sudo python <script_name>"
    If instead the script is executed like "sudo mn --custom <script_name> ...." 
    then you can only use the options added to "topos" and "controllers" list, by specifying parameters to the "sudo mn" command.
    '''
    topo = MyTopo()
    net = Mininet(topo=topo,controller=FLController,link=TCLink,listenPort=6634)

    # specify the MAC addresses for the hosts and switches
    hosts = net.hosts
    hosts[0].setMAC("00:00:00:00:00:01", intf='h1-eth0')
    hosts[1].setMAC("00:00:00:00:00:02", intf='h2-eth0')
    hosts[2].setMAC("00:00:00:00:00:03", intf='h3-eth0')

    switches = net.switches
    switches[0].setMAC("00:00:00:01:00:01", intf='s1-eth1')
    switches[0].setMAC("00:00:00:01:00:02", intf='s1-eth2')
    switches[0].setMAC("00:00:00:01:00:03", intf='s1-eth3')
    switches[0].setMAC("00:00:00:01:00:04", intf='s1-eth4')
    switches[0].setMAC("00:00:00:01:00:05", intf='s1-eth5')

    switches[1].setMAC("00:00:00:02:00:01", intf='s2-eth1')
    switches[1].setMAC("00:00:00:02:00:02", intf='s2-eth2')

    switches[2].setMAC("00:00:00:03:00:01", intf='s3-eth1')
    switches[2].setMAC("00:00:00:03:00:02", intf='s3-eth2')

    switches[3].setMAC("00:00:00:04:00:01", intf='s4-eth1')
    switches[3].setMAC("00:00:00:04:00:02", intf='s4-eth2')

    switches[4].setMAC("00:00:00:05:00:01", intf='s5-eth1')
    switches[4].setMAC("00:00:00:05:00:02", intf='s5-eth2')

    switches[5].setMAC("00:00:00:06:00:01", intf='s6-eth1')
    switches[5].setMAC("00:00:00:06:00:02", intf='s6-eth2')
    switches[5].setMAC("00:00:00:06:00:03", intf='s6-eth3')
    switches[5].setMAC("00:00:00:06:00:04", intf='s6-eth4')

    hosts[0].setIP('10.0.0.1/24',intf='h1-eth0')
    hosts[1].setIP('10.0.0.2/24',intf='h2-eth0')
    hosts[2].setIP('10.0.0.9/24',intf='h3-eth0')

    net.start()
    print "Dumping host connections"
    dumpNodeConnections(net.hosts)
    dumpNetConnections(net)
    CLI(net)
    net.stop()

if __name__ == '__main__':
    '''
    Whenever the script is executed with python instead of being a parameter to "sudo mn"
    this part is executed. So the perfTest() function is called. 
    '''
    setLogLevel('info')   
    perfTest()
