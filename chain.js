var Block = require('./block');

const difficulty = 5;
var chain = [];

function createGenesisBlock() {
    return new Block(Date.parse("2017-01-01"), {}, "0");
}

class BlockChain {
    constructor() {       
                
    }

    init() {
        chain.push(createGenesisBlock());
    }

    getLatestBlock() {
        return chain[chain.length - 1];
    } 

    hasBlockForDeviceId(id) {
        for(let i = 1; i<chain.length;i++) {
            if(id === chain[i].device.id) {
                return true;
            }
        }
        return false;
    }
    
    addBlock(newBlock) {
        newBlock.previousHash = this.getLatestBlock().hash;
        newBlock.mineBlock(difficulty);
        chain.push(newBlock);
    }

    isChainValid() {
        for (let i = 1; i < chain.length; i++) {
            const currentBlock = chain[i];
            const previousBlock = chain[i - 1];
            if (currentBlock.hash !== currentBlock.calculateHash()) {
                return false;
            }
            if (currentBlock.previousHash !== previousBlock.hash) {
                return false;
            }
        }
        return true;
    }

    getChain() {
        return [].concat(chain);
    }
}

module.exports = BlockChain;