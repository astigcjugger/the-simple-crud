import { Customer } from './Customer';
import { Office } from './Office';

export class Address {
    id: number;
    address1: string;
    address2: string;
    city: string;
    state: string;
    zipCode: string;
    customerAddress: Customer;
    officeAddress: Office;
}
