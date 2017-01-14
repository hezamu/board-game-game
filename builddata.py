import os
from subprocess import Popen, PIPE

print '['

for f in os.listdir('public/pics/'):
    cmd = ['identify', '-format', '\'%w %h\'', 'public/pics/' + f]
    pipe = Popen(cmd, stdin=PIPE, stdout=PIPE, stderr=PIPE)
    output, err = pipe.communicate()

    name = ''
    lastnum = False
    for ch in f[:-4]:
        if ch.isupper(): name = name + ' '

        if lastnum == False and ch.isdigit():
            name = name + ' '
            lastnum = True

        lastnum = ch.isdigit()

        name = name + ch
    w, h = output[1:-1].split()
    print '{ "name": "' + name[1:] + '", "img": "pics/' + f + '", "width": ' + w + ', "height": ' + h + ' },'

print ']'
