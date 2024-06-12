
from sys import modules
from inspect import getmembers, isfunction, isclass

# This should probably be done using MetaClasses.

def create_node_wrapper_class(name):
    table = dict([ (n, o) for n, o in getmembers(modules[__name__]) \
                   if isfunction(o) or isclass(o) ])
    out_class = table[name + "_out"]
    mem_class = table[name + "_mem"]
    step_fun = table[name + "_step"]
    reset_fun = table[name + "_reset"]

    def wrapper_init(self):
        self.mem = mem_class()
        reset_fun(self.mem)

    def wrapper_reset(self):
        reset_fun(self.mem)

    def wrapper_step(self, *args):
        out = out_class()
        step_fun(*args, out, self.mem)
        return out

    return type(name.capitalize(), (), {
        '__init__': wrapper_init,
        'step': wrapper_step,
        'reset': wrapper_reset,
    })

# For now, we only create wrappers for nodes that have memory.

for node_mem in [ n for n, o in getmembers(modules[__name__]) \
                  if isclass(o) and n.endswith("_mem") ]:
    node = node_mem.replace("_mem", "")
    globals()[node.capitalize()] = create_node_wrapper_class(node)
