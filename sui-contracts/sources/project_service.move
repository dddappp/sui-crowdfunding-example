module sui_crowdfunding_example::project_service {

    use sui::clock::Clock;
    use sui::coin;
    use sui::coin::Coin;
    use sui::transfer;
    use sui::tx_context;
    use sui::tx_context::TxContext;

    use sui_crowdfunding_example::project::Project;
    use sui_crowdfunding_example::project_aggregate;

    public entry fun donate<T>(
        project: &mut Project<T>,
        coin: &mut Coin<T>,
        clock: &Clock,
        amount: u64,
        ctx: &mut TxContext,
    ) {
        let a = coin::split(coin, amount, ctx);
        project_aggregate::donate(project, coin::into_balance(a), clock, ctx);
    }

    #[allow(lint(self_transfer))]
    public fun withdraw<T>(
        project: &mut Project<T>,
        clock: &Clock,
        ctx: &mut TxContext,
    ) {
        let a = project_aggregate::withdraw(project, clock, ctx);
        let c = coin::from_balance(a, ctx);
        transfer::public_transfer(c, tx_context::sender(ctx));
    }

    #[allow(lint(self_transfer))]
    public fun refund<T>(
        project: &mut Project<T>,
        clock: &Clock,
        ctx: &mut TxContext,
    ) {
        let a = project_aggregate::refund(project, clock, ctx);
        let c = coin::from_balance(a, ctx);
        transfer::public_transfer(c, tx_context::sender(ctx));
    }
}
