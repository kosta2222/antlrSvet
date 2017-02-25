import org.antlr.v4.runtime.misc.NotNull;

class svetBaseVisitorImpl extends svetBaseVisitor<Double>{
    double vatsForOursLamp=0;
    double rub=0;
    double dni=0;
    @Override
    public Double visitInput(@NotNull svetParser.InputContext ctx) {
        return visit(ctx.svet());
             }

    @Override
    public Double visitSvet(@NotNull svetParser.SvetContext ctx) {
        if(ctx.comp()!=null && ctx.lamp()!=null) {
            return (visit(ctx.lamp()) + visit(ctx.comp())) * (visit(ctx.rub()) / 1000.0) * visit(ctx.n());
        }else if(ctx.comp()==null){
            return (visit(ctx.lamp()) * (visit(ctx.rub()) / 1000.0)) * visit(ctx.n());
        }
        else if(ctx.lamp()==null){
            return (visit(ctx.comp()) * (visit(ctx.rub()) / 1000.0)) * visit(ctx.n());
        }
        return null;
    }

    @Override
    public Double visitToLamp(@NotNull svetParser.ToLampContext ctx) {
        //return super.visitToLamp(ctx);
        vatsForOursLamp=(visit(ctx.kolvoVat())*visit(ctx.amount()))*visit(ctx.rab());
        return vatsForOursLamp;
    }

    @Override
    public Double visitToAmount(@NotNull svetParser.ToAmountContext ctx) {
        return visit(ctx.atom());
    }

    @Override
    public Double visitToRab(@NotNull svetParser.ToRabContext ctx) {
        return visit(ctx.atom());
    }

    @Override
    public Double visitToKolVat(@NotNull svetParser.ToKolVatContext ctx) {
        return visit(ctx.atom());
    }

    @Override
    public Double visitToInt(@NotNull svetParser.ToIntContext ctx) {
        return Double.parseDouble(ctx.INT().getText());
    }

    @Override
    public Double visitToDouble(@NotNull svetParser.ToDoubleContext ctx) {
        return Double.parseDouble(ctx.DOUBLE().getText());
    }

    @Override
    public Double visitToRub(@NotNull svetParser.ToRubContext ctx) {
        rub=visit(ctx.atom());
        return rub;
    }

    @Override
    public Double visitToN(@NotNull svetParser.ToNContext ctx) {
         dni= visit(ctx.atom());
        return dni;
    }

    @Override
    public Double visitToComp(@NotNull svetParser.ToCompContext ctx) {
      double  vatsForOursComp=(visit(ctx.kolvoVat())*visit(ctx.amount()))*visit(ctx.rab());
        return vatsForOursComp;
    }
}