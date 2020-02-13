import { Address } from './Address';
import { Office } from './Office';

export class Customer {
    id: number;
    customerName: string;
    emailAddress: string;
    workPhone: string;
    homePhone: string;
    mobilePhone: string;
    address: Address;
    offices: Office[];
}
