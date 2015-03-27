import thread
import urllib
import time

# Time start system
t1 = time.time()

# List site for read
list_url = ('http://www.mail.ru',
'http://www.ukr.net',
'http://www.python.org',
'http://www.pandora.com'
)

# Function for work which thread  
def read_site(number, url, lock):
    t1 = time.time()
    r = urllib.urlopen(url)
    t2 = time.time()
    print "#%s, site: %s, time: %.2f" % (number, url, (t2-t1))
# Free object
    lock.release()

# List objects for lock
lock_list = []
for number, url in enumerate(list_url):
# Create new object for lock. In the begin lock in the false
    lock = thread.allocate_lock()
# Lock object
    lock.acquire()
# remember our lock
    lock_list.append(lock)
# Start new thread and start our function
    thread.start_new_thread(read_site, (number, url, lock))

# Wait when all threads will be finish 
while(any([l.locked() for l in lock_list])):
    time.sleep(2)
# Time when program will be close  
t2 = time.time()
print 'Execution of all threads is complete, time: %.2f' % (t2-t1)