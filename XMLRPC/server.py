from SimpleXMLRPCServer import SimpleXMLRPCServer
import datetime, socket, xmlrpclib, random, array

# Create server
server = SimpleXMLRPCServer(("localhost", 8888))

# Register an instance; all the methods of the instance are 
# published as XML-RPC methods
class MyFuncs:
	def add(self, x, y): 
		print
		print "input x=%s, y=%s" % (str(x), str(y))
		sum = x + y
		print "output", sum
		print
		return sum
	def addOneDay(self, now):
		now = datetime.datetime.strptime(str(now), "%Y%m%dT%H:%M:%S")
		print
		print "input date", now
		now += datetime.timedelta(1)
		print "output date", now
		print
		return now
	def getHostName(self, android):
		host = socket.gethostname()
		return "android: " + android + "\nhost: " + host
	def testStruct(self, map):
		print
		print "input map", map
		map["Temperature"] = 24
		print "output map", map
		print
		return map
	def testArray(self, inArray):
		print
		print "input array", inArray
		sum = 0
		for i in inArray:
			sum += i
		outArray = [sum, len(inArray)]
		# could also return [sum, len(inArray)]
		print "output array", outArray
		print
		return outArray
	def desaturateImage(self, img):
		arr = array.array('I', img.data)
		i = 0
		for c in arr:
			b = c & 255
			c >>= 8
			g = c & 255
			c >>= 8
			r = c & 255
			c >>= 8
			a = c & 255
			grey = (r + g +b) / 3
			c = a
			c <<= 8
			c += grey
			c <<= 8
			c += grey
			c <<= 8
			c += grey
			arr[i] = c
			i += 1
		return  xmlrpclib.Binary(arr.tostring())
	def invertBoolean(self, b):
		return not b
	def getHugeString(self):
		return "x" * 1024 * 64
	def get2DArray(self):
		arr = []
		arr.append([1,2,3])
		arr.append([4,5,6])
		arr.append([{"x": 1, "y":0, "z":0},8,9])
		print
		print "output", arr
		print
		return arr
	def getCountry(self, person):
		firstName = person["firstName"]
		lastName = person["lastName"]
		location = firstName + " " + lastName + " lives in "
		if firstName == "John" and lastName == "Smith":
			location += "USA"
		elif firstName == "Hans" and lastName == "Muller":
			location += "Germany"
		else:
			location += "Spain"
		return location

    
server.register_instance(MyFuncs())

# Run the server's main loop
server.serve_forever()
