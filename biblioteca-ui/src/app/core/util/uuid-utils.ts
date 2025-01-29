import { v4 as uuidv4 } from 'uuid';

export class UUIDUtils {
    generateId(): string {
        return uuidv4();
    }
}
