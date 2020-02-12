import { Address } from './Address';
import { Customer } from './Customer';

export class Office {
    id: number;
    officeName: string;
    mainContact: string;
    mainPhone: string;
    supportPhone: string;
    address: Address;
    customers: Set<Customer> = new Set<Customer>();
}
