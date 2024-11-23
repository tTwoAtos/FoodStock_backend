import dapr
import subprocess
import kubernetes
from dataclasses import dataclass as dtc

@dtc
class DaprObject:
    name: str

class DaprRuntime():
    def __init_subclass__(cls) -> None:
        pass

class DaprBuild():
    def __init_subclass__(cls) -> None:
        pass