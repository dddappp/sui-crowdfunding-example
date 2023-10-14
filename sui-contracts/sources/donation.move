// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_crowdfunding_example::donation {
    friend sui_crowdfunding_example::project_create_logic;
    friend sui_crowdfunding_example::project_update_logic;
    friend sui_crowdfunding_example::project_start_logic;
    friend sui_crowdfunding_example::project_donate_logic;
    friend sui_crowdfunding_example::project_withdraw_logic;
    friend sui_crowdfunding_example::project_refund_logic;
    friend sui_crowdfunding_example::project;

    const EDATA_TOO_LONG: u64 = 102;

    struct Donation has store {
        donator: address,
        amount: u64,
    }

    public fun donator(donation: &Donation): address {
        donation.donator
    }

    public fun amount(donation: &Donation): u64 {
        donation.amount
    }

    public(friend) fun set_amount(donation: &mut Donation, amount: u64) {
        donation.amount = amount;
    }

    public(friend) fun new_donation(
        donator: address,
        amount: u64,
    ): Donation {
        Donation {
            donator,
            amount,
        }
    }

    public(friend) fun drop_donation(donation: Donation) {
        let Donation {
            donator: _,
            amount: _,
        } = donation;
    }


}
