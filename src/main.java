/*Name: Amutha
Date: 28/08/2024
Des: 2. Testing REST API using Rest Assured:
Implement automated tests for the API endpoints using Rest Assured.
Use Request and Response Specifications to streamline and manage repetitive code.
Log request and response details to facilitate debugging.
Assert response codes (e.g., 200 OK, 201 Created) and verify response bodies using jsonpath().
Handle API parameters such as base URI, query parameters, path parameters, request bodies, and headers.
Conduct end-to-end testing to ensure the entire system functions correctly and integrates well.*/






const express = require('express');
const app = express();
const port = 3000;
app.use(express.json());
let deviceList = [
{ id: 1, type: 'camera', watt: '100' },
{ id: 2, type: 'bulb', watt: '200' }
];
let schedule = [
{ id: 1, operation: 'turn on bulb at 7 pm'},
{ id: 2, operation: 'click photo at 8 pm',}
];




// Get all devices
app.get('/devices', (req, res) => {
res.json(deviceList);
});
// Get a specific devices by ID
app.get('/devices/:deviceId', (req, res) => {
const device = deviceList.find(dev => dev.id === parseInt(req.params.deviceId));
if (!device) return res.status(404).send('device not found');
res.json(device);
});


// Add a new devices
app.post('/devices', (req, res) => {
const { type, watt } = req.body;
const newDevice = {
id: deviceList.length + 1,
type,
watt};
deviceList.push(newDevice);
res.status(201).json(newDevice);
});
// Update an existing devices
app.put('/devices/:deviceId', (req, res) => {
const device = deviceList.find(dev => dev.id === parseInt(req.params.deviceId));
if (!device) return res.status(404).send('device not found');
const { type, watt } = req.body;
device.type = type;
device.watt = watt;
res.json(device);
});
// Delete an devices
app.delete('/devices/:deviceId', (req, res) => {
const deviceIndex = deviceList.findIndex(dev => dev.id === parseInt(req.params.deviceId));
if (deviceIndex === -1) return res.status(404).send('device not found');
const deleteddevice = deviceList.splice(deviceIndex, 1);
res.json(deleteddevice);
});
// Add a new operation
app.post('/schedules', (req, res) => {
const { operation } = req.body;
const newOperation = {
id: schedule.length + 1,
operation
};
schedule.push(newOperation);
res.status(201).json(newOperation);
});
// Get all schedules
app.get('/schedules', (req, res) => {
res.json(schedule);
});
// Get a specific schedules by ID
app.get('/schedules/:scheduleId', (req, res) => {
const schedules = schedule.find(sch => sch.id === parseInt(req.params.scheduleId));
if (!schedules) return res.status(404).send('schedule not found');
res.json(schedules);
});


app.listen(port, () => {
console.log(`Server is running on http://localhost:${port}`); });