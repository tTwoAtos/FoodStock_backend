import docker
import subprocess
from dataclasses import dataclass as dtc

@dtc
class Dockerfile:
    name: str
    url: str

class Build(Dockerfile):
    def __init_subclass__(cls) -> None:
        return super().__init_subclass__()
    
    @classmethod
    def define_docker_context(cls) -> None:
        return docker.context()
    


if __name__ == "__main__":
    docker_name = input('Docker name of image')
    docker_url = input('Dockerfile to build: ')
    build_package = Build(docker_name, docker_url)
    subprocess.call()