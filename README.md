# Network Behaviour in Swarm

The following docker stack consists of 3 containers:

* Spring Cloud Discovery Server
* Spring Cloud Config Server
* Simple Spring Boot App that connects to discovery server to find config server.

# Problem encountered

When the config server registers with the discovery server, it does so with the ip address from the ingress network rather than the created demo-network. The simple boot application cannot hit that ip address on the ingress network and so it never comes online. 

# Versions

Spring Boot: 1.5.3 RELEASE
Spring Cloud: Camden.SR6

$ docker version
Client:
 Version:      17.03.1-ce
 API version:  1.27
 Go version:   go1.7.5
 Git commit:   c6d412e
 Built:        Tue Mar 28 00:40:02 2017
 OS/Arch:      darwin/amd64

Server:
 Version:      17.05.0-ce
 API version:  1.29 (minimum version 1.12)
 Go version:   go1.7.5
 Git commit:   89658be
 Built:        Thu May  4 21:43:09 2017
 OS/Arch:      linux/amd64
 Experimental: false

$ docker info
Containers: 0
 Running: 0
 Paused: 0
 Stopped: 0
Images: 22
Server Version: 17.05.0-ce
Storage Driver: aufs
 Root Dir: /mnt/sda1/var/lib/docker/aufs
 Backing Filesystem: extfs
 Dirs: 20
 Dirperm1 Supported: true
Logging Driver: json-file
Cgroup Driver: cgroupfs
Plugins:
 Volume: local
 Network: bridge host macvlan null overlay
Swarm: active
 NodeID: 9x7n5pnsq03ejs84snhn9py42
 Is Manager: true
 ClusterID: q2wj5xm2d72q7el58eawycz0y
 Managers: 1
 Nodes: 2
 Orchestration:
  Task History Retention Limit: 5
 Raft:
  Snapshot Interval: 10000
  Number of Old Snapshots to Retain: 0
  Heartbeat Tick: 1
  Election Tick: 3
 Dispatcher:
  Heartbeat Period: 5 seconds
 CA Configuration:
  Expiry Duration: 3 months
 Node Address: 192.168.99.101
 Manager Addresses:
  192.168.99.101:2377
Runtimes: runc
Default Runtime: runc
Init Binary: docker-init
containerd version: 9048e5e50717ea4497b757314bad98ea3763c145
runc version: 9c2d8d184e5da67c95d601382adf14862e4f2228
init version: 949e6fa
Security Options:
 seccomp
  Profile: default
Kernel Version: 4.4.66-boot2docker
Operating System: Boot2Docker 17.05.0-ce (TCL 7.2); HEAD : 5ed2840 - Fri May  5 21:04:09 UTC 2017
OSType: linux
Architecture: x86_64
CPUs: 1
Total Memory: 995.8 MiB
Name: myvm1
ID: 7ROG:TY64:UKPU:IGKB:FFI4:5UXE:WFEJ:CNBQ:QWWJ:6YOV:WP4K:IWRX
Docker Root Dir: /mnt/sda1/var/lib/docker
Debug Mode (client): false
Debug Mode (server): true
 File Descriptors: 31
 Goroutines: 133
 System Time: 2017-05-10T21:43:25.495630861Z
 EventsListeners: 0
Username: kramkroc
Registry: https://index.docker.io/v1/
Labels:
 provider=virtualbox
Experimental: false
Insecure Registries:
 127.0.0.0/8
Live Restore Enabled: false

# Build and Run

Create two vms, although I only use one here:

```sh
docker-machine create myvm1
docker-machine create myvm2
```

Then set first to be the active one in docker-machine:

```sh
eval $(docker-machine env myvm1)
```

Create images on that vm using the following command in the root folder for this project:

```sh
./gradlew buildDocker
```

Activate swarm mode:

```sh
docker swarm init
```

Create overlay network for swarm:

```sh
docker network create -d overlay demo-network
```

Deploy the stack using the compose file:

```
docker stack deploy -c docker-compose.yml demostack
```
