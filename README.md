This is a test case for the bug at https://github.com/msigwart/fakeload/issues/4

This assumes you are running docker on Linux and that you have the privileges to execute docker. You may need to execute some scripts with sudo.

Make sure that docker is setup to listen on localhost:5010 for connections
If you put the following in the systemctl overrides that will take care of this (`systemctl edit docker`)

```
[Service]
ExecStart=
ExecStart=/usr/sbin/dockerd -H unix:// -H tcp://localhost:5010 $DOCKER_OPTS
```

Execute the following to run the test

1. `./build-docker.sh`
1. `./create-container.sh`
1. `./run-container.sh`

The fake load server is being told to use 75% of the CPU. 
When I run this on a 12 core server outside of docker and watch top I see java at 900% CPU.
9 out of 12 CPUs is 75%, so this works.
When I run inside the docker container that is limited to a single CPU, I still see the java process at 900% CPU when in fact it should be at 75% CPU since the docker container only has access to 1 CPU.
If this is running in an environment where docker is limiting the container to only the specified CPUs, then the fake load library is never able to attain the desired 75% load.

