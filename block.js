const SHA256 = require("crypto-js/sha256");

class Block {
    constructor(timestamp, device, previousHash = '') {
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.device = device;
        this.hash = this.calculateHash();
        this.nonce = 0;
    }

    calculateHash() {
        return SHA256(this.previousHash + this.timestamp + JSON.stringify(this.device) + this.nonce).toString();
    }

    mineBlock(difficulty) {
        while (this.hash.substring(0, difficulty) !== Array(difficulty + 1).join("0")) {
            this.nonce++;
            this.hash = this.calculateHash();
        }
        console.log("BLOCK MINED: " + this.hash);
    }
}

module.exports = Block;