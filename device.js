var uuidv4 = require('uuid/v4');

class Device {
    constructor(id) {
        this.id = id;
        this.token = uuidv4();
    }
}

module.exports = Device;