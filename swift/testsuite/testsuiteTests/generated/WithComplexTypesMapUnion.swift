import Foundation
import SwiftyJSON

public enum WithComplexTypesMapUnion: JSONSerializable, DataTreeSerializable, Equatable {
    
    case IntMember(Int)
    
    case StringMember(String)
    
    case SimpleMember(Simple)
    case UNKNOWN$([String : AnyObject])
    
    public static func readJSON(json: JSON) -> WithComplexTypesMapUnion {
        let dict = json.dictionaryValue
        if let member = dict["int"] {
            return .IntMember(member.intValue)
        }
        if let member = dict["string"] {
            return .StringMember(member.stringValue)
        }
        if let member = dict["org.coursera.records.test.Simple"] {
            return .SimpleMember(Simple.readJSON(member.jsonValue))
        }
        return .UNKNOWN$(json.dictionaryObject!)
    }
    public func writeJSON() -> JSON {
        return JSON(self.writeData())
    }
    public static func readData(data: [String: AnyObject]) -> WithComplexTypesMapUnion {
        return readJSON(JSON(data))
    }
    public func writeData() -> [String: AnyObject] {
        switch self {
        case .IntMember(let member):
            return ["int": member];
        case .StringMember(let member):
            return ["string": member];
        case .SimpleMember(let member):
            return ["org.coursera.records.test.Simple": member.writeData()];
        case .UNKNOWN$(let dict):
            return dict
        }
    }
}

public func ==(lhs: WithComplexTypesMapUnion, rhs: WithComplexTypesMapUnion) -> Bool {
    switch (lhs, rhs) {
    case (let .IntMember(lhs), let .IntMember(rhs)):
        return lhs == rhs
    case (let .StringMember(lhs), let .StringMember(rhs)):
        return lhs == rhs
    case (let .SimpleMember(lhs), let .SimpleMember(rhs)):
        return lhs == rhs
    case (let .UNKNOWN$(lhs), let .UNKNOWN$(rhs)):
        return JSON(lhs) == JSON(rhs)
    default:
        return false
    }
}