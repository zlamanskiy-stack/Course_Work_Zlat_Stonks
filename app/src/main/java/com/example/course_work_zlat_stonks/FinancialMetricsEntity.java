package com.example.course_work_zlat_stonks;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "cw_database")
public class FinancialMetricsEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;

    public String getTicker() {
        return Ticker;
    }

    public void setTicker(String ticker) {
        Ticker = ticker;
    }

    public Double getMarket_Cap() {
        return Market_Cap;
    }

    public void setMarket_Cap(Double market_Cap) {
        Market_Cap = market_Cap;
    }

    @ColumnInfo(name = "Ticker") private String Ticker;
    @ColumnInfo(name = "Market_Cap") private Double Market_Cap;
    @ColumnInfo(name = "Enterprise_Value") private Double Enterprise_Value;
    @ColumnInfo(name = "PE_Trailing") private Double PE_Trailing;
    @ColumnInfo(name = "PE_Forward") private Double PE_Forward;
    @ColumnInfo(name = "PEG_Ratio") private Double PEG_Ratio;
    @ColumnInfo(name = "PBV") private Double PBV;
    @ColumnInfo(name = "PS") private Double PS;
    @ColumnInfo(name = "PCF_TTM") private Double PCF_TTM;
    @ColumnInfo(name = "PFCF") private Double PFCF;
    @ColumnInfo(name = "PE_Relative") private Double PE_Relative;
    @ColumnInfo(name = "Shiller_PE") private Double Shiller_PE;
    @ColumnInfo(name = "PTBV") private Double PTBV;
    @ColumnInfo(name = "PEBIT") private Double PEBIT;
    @ColumnInfo(name = "PEBITDA") private Double PEBITDA;
    @ColumnInfo(name = "PEGY") private Double PEGY;
    @ColumnInfo(name = "EV_Sales") private Double EV_Sales;
    @ColumnInfo(name = "EV_EBITDA") private Double EV_EBITDA;
    @ColumnInfo(name = "EV_EBIT") private Double EV_EBIT;
    @ColumnInfo(name = "EV_GP") private Double EV_GP;
    @ColumnInfo(name = "EV_Invested_Capital") private Double EV_Invested_Capital;
    @ColumnInfo(name = "EV_FCF") private Double EV_FCF;
    @ColumnInfo(name = "EV_Total_Assets") private Double EV_Total_Assets;
    @ColumnInfo(name = "Gross_Margin") private Double Gross_Margin;
    @ColumnInfo(name = "Operating_Margin_ROS") private Double Operating_Margin_ROS;
    @ColumnInfo(name = "Net_Profit_Margin") private Double Net_Profit_Margin;
    @ColumnInfo(name = "Pretax_Margin") private Double Pretax_Margin;
    @ColumnInfo(name = "EBITDA_Margin") private Double EBITDA_Margin;
    @ColumnInfo(name = "EBIT_Margin") private Double EBIT_Margin;
    @ColumnInfo(name = "FCF_Margin") private Double FCF_Margin;
    @ColumnInfo(name = "ROA") private Double ROA;
    @ColumnInfo(name = "ROAA") private Double ROAA;
    @ColumnInfo(name = "ROE") private Double ROE;
    @ColumnInfo(name = "ROACE") private Double ROACE;
    @ColumnInfo(name = "ROIC") private Double ROIC;
    @ColumnInfo(name = "ROCE") private Double ROCE;
    @ColumnInfo(name = "ROE_DuPont_3") private Double ROE_DuPont_3;
    @ColumnInfo(name = "ROE_DuPont_5") private Double ROE_DuPont_5;
    @ColumnInfo(name = "CFROI") private Double CFROI;
    @ColumnInfo(name = "CROCI") private Double CROCI;
    @ColumnInfo(name = "RONA") private Double RONA;
    @ColumnInfo(name = "ROTC") private Double ROTC;
    @ColumnInfo(name = "ROIC_Pretax") private Double ROIC_Pretax;
    @ColumnInfo(name = "SG_and_A_to_Sales") private Double SG_and_A_to_Sales;
    @ColumnInfo(name = "R_and_D_to_Sales") private Double R_and_D_to_Sales;
    @ColumnInfo(name = "Capex_to_Sales") private Double Capex_to_Sales;
    @ColumnInfo(name = "Capex_to_Depreciation") private Double Capex_to_Depreciation;
    @ColumnInfo(name = "Operating_Leverage") private Double Operating_Leverage;
    @ColumnInfo(name = "Financial_Leverage") private Double Financial_Leverage;
    @ColumnInfo(name = "Current_Ratio") private Double Current_Ratio;
    @ColumnInfo(name = "Quick_Ratio") private Double Quick_Ratio;
    @ColumnInfo(name = "Cash_Ratio") private Double Cash_Ratio;
    @ColumnInfo(name = "Operating_Cash_Flow_Ratio") private Double Operating_Cash_Flow_Ratio;
    @ColumnInfo(name = "Defensive_Interval_Ratio") private Double Defensive_Interval_Ratio;
    @ColumnInfo(name = "Debt_Equity") private Double Debt_Equity;
    @ColumnInfo(name = "Debt_Assets") private Double Debt_Assets;
    @ColumnInfo(name = "Debt_Capital") private Double Debt_Capital;
    @ColumnInfo(name = "Net_Debt_Equity") private Double Net_Debt_Equity;
    @ColumnInfo(name = "Net_Debt_EBITDA") private Double Net_Debt_EBITDA;
    @ColumnInfo(name = "Net_Debt_EBIT") private Double Net_Debt_EBIT;
    @ColumnInfo(name = "Gross_Debt_EBITDA") private Double Gross_Debt_EBITDA;
    @ColumnInfo(name = "EBITDA_Interest") private Double EBITDA_Interest;
    @ColumnInfo(name = "ICR") private Double ICR;
    @ColumnInfo(name = "Fixed_Charge_Coverage") private Double Fixed_Charge_Coverage;
    @ColumnInfo(name = "DSCR") private Double DSCR;
    @ColumnInfo(name = "Leverage_Ratio") private Double Leverage_Ratio;
    @ColumnInfo(name = "Equity_Multiplier") private Double Equity_Multiplier;
    @ColumnInfo(name = "Tier1_Capital_Ratio") private Double Tier1_Capital_Ratio;
    @ColumnInfo(name = "Inventory_Turnover") private Double Inventory_Turnover;
    @ColumnInfo(name = "DIO_days") private Double DIO_days;
    @ColumnInfo(name = "Receivables_Turnover") private Double Receivables_Turnover;
    @ColumnInfo(name = "DSO_days") private Double DSO_days;
    @ColumnInfo(name = "Payables_Turnover") private Double Payables_Turnover;
    @ColumnInfo(name = "DPO_days") private Double DPO_days;
    @ColumnInfo(name = "CCC_days") private Double CCC_days;
    @ColumnInfo(name = "Asset_Turnover") private Double Asset_Turnover;
    @ColumnInfo(name = "Fixed_Asset_Turnover") private Double Fixed_Asset_Turnover;
    @ColumnInfo(name = "Working_Capital_Turnover") private Double Working_Capital_Turnover;
    @ColumnInfo(name = "Equity_Turnover") private Double Equity_Turnover;
    @ColumnInfo(name = "Revenue_Growth_YoY") private Double Revenue_Growth_YoY;
    @ColumnInfo(name = "Revenue_Growth_3Y_CAGR") private Double Revenue_Growth_3Y_CAGR;
    @ColumnInfo(name = "Revenue_Growth_5Y_CAGR") private Double Revenue_Growth_5Y_CAGR;
    @ColumnInfo(name = "EBITDA_Growth_YoY") private Double EBITDA_Growth_YoY;
    @ColumnInfo(name = "EBITDA_Growth_3Y_CAGR") private Double EBITDA_Growth_3Y_CAGR;
    @ColumnInfo(name = "EBITDA_Growth_5Y_CAGR") private Double EBITDA_Growth_5Y_CAGR;
    @ColumnInfo(name = "EBIT_Growth_YoY") private Double EBIT_Growth_YoY;
    @ColumnInfo(name = "EBIT_Growth_3Y_CAGR") private Double EBIT_Growth_3Y_CAGR;
    @ColumnInfo(name = "EBIT_Growth_5Y_CAGR") private Double EBIT_Growth_5Y_CAGR;
    @ColumnInfo(name = "EPS_Growth_YoY") private Double EPS_Growth_YoY;
    @ColumnInfo(name = "EPS_Growth_3Y_CAGR") private Double EPS_Growth_3Y_CAGR;
    @ColumnInfo(name = "EPS_Growth_5Y_CAGR") private Double EPS_Growth_5Y_CAGR;
    @ColumnInfo(name = "FCF_Growth_YoY") private Double FCF_Growth_YoY;
    @ColumnInfo(name = "FCF_Growth_3Y_CAGR") private Double FCF_Growth_3Y_CAGR;
    @ColumnInfo(name = "FCF_Growth_5Y_CAGR") private Double FCF_Growth_5Y_CAGR;
    @ColumnInfo(name = "BVPS_Growth_YoY") private Double BVPS_Growth_YoY;
    @ColumnInfo(name = "BVPS_Growth_3Y_CAGR") private Double BVPS_Growth_3Y_CAGR;
    @ColumnInfo(name = "BVPS_Growth_5Y_CAGR") private Double BVPS_Growth_5Y_CAGR;
    @ColumnInfo(name = "Sales_Per_Share_Growth_YoY") private Double Sales_Per_Share_Growth_YoY;
    @ColumnInfo(name = "Sales_Per_Share_Growth_3Y_CAGR") private Double Sales_Per_Share_Growth_3Y_CAGR;
    @ColumnInfo(name = "Sales_Per_Share_Growth_5Y_CAGR") private Double Sales_Per_Share_Growth_5Y_CAGR;
    @ColumnInfo(name = "DPS") private Double DPS;
    @ColumnInfo(name = "DPR_Payout_Ratio") private Double DPR_Payout_Ratio;
    @ColumnInfo(name = "Retention_Ratio") private Double Retention_Ratio;
    @ColumnInfo(name = "Sustainable_Growth_Rate") private Double Sustainable_Growth_Rate;
    @ColumnInfo(name = "Dividend_Coverage_Ratio") private Double Dividend_Coverage_Ratio;
    @ColumnInfo(name = "Dividend_Yield") private Double Dividend_Yield;
    @ColumnInfo(name = "FCF_Yield") private Double FCF_Yield;
    @ColumnInfo(name = "Earnings_Yield") private Double Earnings_Yield;
    @ColumnInfo(name = "Buyback_Yield") private Double Buyback_Yield;
    @ColumnInfo(name = "Total_Shareholder_Yield") private Double Total_Shareholder_Yield;
    @ColumnInfo(name = "Graham_Number") private Double Graham_Number;
    @ColumnInfo(name = "Ben_Graham_Formula") private Double Ben_Graham_Formula;
    @ColumnInfo(name = "Peter_Lynch_Fair_Value") private Double Peter_Lynch_Fair_Value;
    @ColumnInfo(name = "Altman_Z_Score") private Double Altman_Z_Score;
    @ColumnInfo(name = "Piotroski_F_Score") private Double Piotroski_F_Score;
    @ColumnInfo(name = "Beta") private Double Beta;
    @ColumnInfo(name = "Alpha") private Double Alpha;
    @ColumnInfo(name = "R_Squared") private Double R_Squared;
    @ColumnInfo(name = "Sharpe_Ratio") private Double Sharpe_Ratio;
    @ColumnInfo(name = "Treynor_Ratio") private Double Treynor_Ratio;
    @ColumnInfo(name = "Sortino_Ratio") private Double Sortino_Ratio;
    @ColumnInfo(name = "Standard_Deviation") private Double Standard_Deviation;
    @ColumnInfo(name = "Value_at_Risk_VaR") private Double Value_at_Risk_VaR;
    @ColumnInfo(name = "Maximum_Drawdown") private Double Maximum_Drawdown;

    public FinancialMetricsEntity() {}

    // ============ ГЕТТЕРЫ ============
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Double getEnterprise_Value() {
        return Enterprise_Value;
    }

    public void setEnterprise_Value(Double enterprise_Value) {
        this.Enterprise_Value = enterprise_Value;
    }

    public Double getPE_Trailing() {
        return PE_Trailing;
    }

    public void setPE_Trailing(Double PE_Trailing) {
        this.PE_Trailing = PE_Trailing;
    }

    public Double getPE_Forward() {
        return PE_Forward;
    }

    public void setPE_Forward(Double PE_Forward) {
        this.PE_Forward = PE_Forward;
    }

    public Double getPEG_Ratio() {
        return PEG_Ratio;
    }

    public void setPEG_Ratio(Double PEG_Ratio) {
        this.PEG_Ratio = PEG_Ratio;
    }

    public Double getPBV() {
        return PBV;
    }

    public void setPBV(Double PBV) {
        this.PBV = PBV;
    }

    public Double getPS() {
        return PS;
    }

    public void setPS(Double PS) {
        this.PS = PS;
    }

    public Double getPCF_TTM() {
        return PCF_TTM;
    }

    public void setPCF_TTM(Double PCF_TTM) {
        this.PCF_TTM = PCF_TTM;
    }

    public Double getPFCF() {
        return PFCF;
    }

    public void setPFCF(Double PFCF) {
        this.PFCF = PFCF;
    }

    public Double getPE_Relative() {
        return PE_Relative;
    }

    public void setPE_Relative(Double PE_Relative) {
        this.PE_Relative = PE_Relative;
    }

    public Double getShiller_PE() {
        return Shiller_PE;
    }

    public void setShiller_PE(Double shiller_PE) {
        Shiller_PE = shiller_PE;
    }

    public Double getPTBV() {
        return PTBV;
    }

    public void setPTBV(Double PTBV) {
        this.PTBV = PTBV;
    }

    public Double getPEBIT() {
        return PEBIT;
    }

    public void setPEBIT(Double PEBIT) {
        this.PEBIT = PEBIT;
    }

    public Double getPEBITDA() {
        return PEBITDA;
    }

    public void setPEBITDA(Double PEBITDA) {
        this.PEBITDA = PEBITDA;
    }

    public Double getPEGY() {
        return PEGY;
    }

    public void setPEGY(Double PEGY) {
        this.PEGY = PEGY;
    }

    public Double getEV_Sales() {
        return EV_Sales;
    }

    public void setEV_Sales(Double EV_Sales) {
        this.EV_Sales = EV_Sales;
    }

    public Double getEV_EBITDA() {
        return EV_EBITDA;
    }

    public void setEV_EBITDA(Double EV_EBITDA) {
        this.EV_EBITDA = EV_EBITDA;
    }

    public Double getEV_EBIT() {
        return EV_EBIT;
    }

    public void setEV_EBIT(Double EV_EBIT) {
        this.EV_EBIT = EV_EBIT;
    }

    public Double getEV_GP() {
        return EV_GP;
    }

    public void setEV_GP(Double EV_GP) {
        this.EV_GP = EV_GP;
    }

    public Double getEV_Invested_Capital() {
        return EV_Invested_Capital;
    }

    public void setEV_Invested_Capital(Double EV_Invested_Capital) {
        this.EV_Invested_Capital = EV_Invested_Capital;
    }

    public Double getEV_FCF() {
        return EV_FCF;
    }

    public void setEV_FCF(Double EV_FCF) {
        this.EV_FCF = EV_FCF;
    }

    public Double getEV_Total_Assets() {
        return EV_Total_Assets;
    }

    public void setEV_Total_Assets(Double EV_Total_Assets) {
        this.EV_Total_Assets = EV_Total_Assets;
    }

    public Double getGross_Margin() {
        return Gross_Margin;
    }

    public void setGross_Margin(Double gross_Margin) {
        Gross_Margin = gross_Margin;
    }

    public Double getOperating_Margin_ROS() {
        return Operating_Margin_ROS;
    }

    public void setOperating_Margin_ROS(Double operating_Margin_ROS) {
        Operating_Margin_ROS = operating_Margin_ROS;
    }

    public Double getNet_Profit_Margin() {
        return Net_Profit_Margin;
    }

    public void setNet_Profit_Margin(Double net_Profit_Margin) {
        Net_Profit_Margin = net_Profit_Margin;
    }

    public Double getPretax_Margin() {
        return Pretax_Margin;
    }

    public void setPretax_Margin(Double pretax_Margin) {
        Pretax_Margin = pretax_Margin;
    }

    public Double getEBITDA_Margin() {
        return EBITDA_Margin;
    }

    public void setEBITDA_Margin(Double EBITDA_Margin) {
        this.EBITDA_Margin = EBITDA_Margin;
    }

    public Double getEBIT_Margin() {
        return EBIT_Margin;
    }

    public void setEBIT_Margin(Double EBIT_Margin) {
        this.EBIT_Margin = EBIT_Margin;
    }

    public Double getFCF_Margin() {
        return FCF_Margin;
    }

    public void setFCF_Margin(Double FCF_Margin) {
        this.FCF_Margin = FCF_Margin;
    }

    public Double getROA() {
        return ROA;
    }

    public void setROA(Double ROA) {
        this.ROA = ROA;
    }

    public Double getROAA() {
        return ROAA;
    }

    public void setROAA(Double ROAA) {
        this.ROAA = ROAA;
    }

    public Double getROE() {
        return ROE;
    }

    public void setROE(Double ROE) {
        this.ROE = ROE;
    }

    public Double getROACE() {
        return ROACE;
    }

    public void setROACE(Double ROACE) {
        this.ROACE = ROACE;
    }

    public Double getROIC() {
        return ROIC;
    }

    public void setROIC(Double ROIC) {
        this.ROIC = ROIC;
    }

    public Double getROCE() {
        return ROCE;
    }

    public void setROCE(Double ROCE) {
        this.ROCE = ROCE;
    }

    public Double getROE_DuPont_3() {
        return ROE_DuPont_3;
    }

    public void setROE_DuPont_3(Double ROE_DuPont_3) {
        this.ROE_DuPont_3 = ROE_DuPont_3;
    }

    public Double getROE_DuPont_5() {
        return ROE_DuPont_5;
    }

    public void setROE_DuPont_5(Double ROE_DuPont_5) {
        this.ROE_DuPont_5 = ROE_DuPont_5;
    }

    public Double getCFROI() {
        return CFROI;
    }

    public void setCFROI(Double CFROI) {
        this.CFROI = CFROI;
    }

    public Double getCROCI() {
        return CROCI;
    }

    public void setCROCI(Double CROCI) {
        this.CROCI = CROCI;
    }

    public Double getRONA() {
        return RONA;
    }

    public void setRONA(Double RONA) {
        this.RONA = RONA;
    }

    public Double getROTC() {
        return ROTC;
    }

    public void setROTC(Double ROTC) {
        this.ROTC = ROTC;
    }

    public Double getROIC_Pretax() {
        return ROIC_Pretax;
    }

    public void setROIC_Pretax(Double ROIC_Pretax) {
        this.ROIC_Pretax = ROIC_Pretax;
    }

    public Double getSG_and_A_to_Sales() {
        return SG_and_A_to_Sales;
    }

    public void setSG_and_A_to_Sales(Double SG_and_A_to_Sales) {
        this.SG_and_A_to_Sales = SG_and_A_to_Sales;
    }

    public Double getR_and_D_to_Sales() {
        return R_and_D_to_Sales;
    }

    public void setR_and_D_to_Sales(Double r_and_D_to_Sales) {
        R_and_D_to_Sales = r_and_D_to_Sales;
    }

    public Double getCapex_to_Sales() {
        return Capex_to_Sales;
    }

    public void setCapex_to_Sales(Double capex_to_Sales) {
        Capex_to_Sales = capex_to_Sales;
    }

    public Double getCapex_to_Depreciation() {
        return Capex_to_Depreciation;
    }

    public void setCapex_to_Depreciation(Double capex_to_Depreciation) {
        Capex_to_Depreciation = capex_to_Depreciation;
    }

    public Double getOperating_Leverage() {
        return Operating_Leverage;
    }

    public void setOperating_Leverage(Double operating_Leverage) {
        Operating_Leverage = operating_Leverage;
    }

    public Double getFinancial_Leverage() {
        return Financial_Leverage;
    }

    public void setFinancial_Leverage(Double financial_Leverage) {
        Financial_Leverage = financial_Leverage;
    }

    public Double getCurrent_Ratio() {
        return Current_Ratio;
    }

    public void setCurrent_Ratio(Double current_Ratio) {
        Current_Ratio = current_Ratio;
    }

    public Double getQuick_Ratio() {
        return Quick_Ratio;
    }

    public void setQuick_Ratio(Double quick_Ratio) {
        Quick_Ratio = quick_Ratio;
    }

    public Double getCash_Ratio() {
        return Cash_Ratio;
    }

    public void setCash_Ratio(Double cash_Ratio) {
        Cash_Ratio = cash_Ratio;
    }

    public Double getOperating_Cash_Flow_Ratio() {
        return Operating_Cash_Flow_Ratio;
    }

    public void setOperating_Cash_Flow_Ratio(Double operating_Cash_Flow_Ratio) {
        Operating_Cash_Flow_Ratio = operating_Cash_Flow_Ratio;
    }

    public Double getDefensive_Interval_Ratio() {
        return Defensive_Interval_Ratio;
    }

    public void setDefensive_Interval_Ratio(Double defensive_Interval_Ratio) {
        Defensive_Interval_Ratio = defensive_Interval_Ratio;
    }

    public Double getDebt_Equity() {
        return Debt_Equity;
    }

    public void setDebt_Equity(Double debt_Equity) {
        Debt_Equity = debt_Equity;
    }

    public Double getDebt_Assets() {
        return Debt_Assets;
    }

    public void setDebt_Assets(Double debt_Assets) {
        Debt_Assets = debt_Assets;
    }

    public Double getDebt_Capital() {
        return Debt_Capital;
    }

    public void setDebt_Capital(Double debt_Capital) {
        Debt_Capital = debt_Capital;
    }

    public Double getNet_Debt_Equity() {
        return Net_Debt_Equity;
    }

    public void setNet_Debt_Equity(Double net_Debt_Equity) {
        Net_Debt_Equity = net_Debt_Equity;
    }

    public Double getNet_Debt_EBITDA() {
        return Net_Debt_EBITDA;
    }

    public void setNet_Debt_EBITDA(Double net_Debt_EBITDA) {
        Net_Debt_EBITDA = net_Debt_EBITDA;
    }

    public Double getNet_Debt_EBIT() {
        return Net_Debt_EBIT;
    }

    public void setNet_Debt_EBIT(Double net_Debt_EBIT) {
        Net_Debt_EBIT = net_Debt_EBIT;
    }

    public Double getGross_Debt_EBITDA() {
        return Gross_Debt_EBITDA;
    }

    public void setGross_Debt_EBITDA(Double gross_Debt_EBITDA) {
        Gross_Debt_EBITDA = gross_Debt_EBITDA;
    }

    public Double getEBITDA_Interest() {
        return EBITDA_Interest;
    }

    public void setEBITDA_Interest(Double EBITDA_Interest) {
        this.EBITDA_Interest = EBITDA_Interest;
    }

    public Double getICR() {
        return ICR;
    }

    public void setICR(Double ICR) {
        this.ICR = ICR;
    }

    public Double getFixed_Charge_Coverage() {
        return Fixed_Charge_Coverage;
    }

    public void setFixed_Charge_Coverage(Double fixed_Charge_Coverage) {
        Fixed_Charge_Coverage = fixed_Charge_Coverage;
    }

    public Double getDSCR() {
        return DSCR;
    }

    public void setDSCR(Double DSCR) {
        this.DSCR = DSCR;
    }

    public Double getLeverage_Ratio() {
        return Leverage_Ratio;
    }

    public void setLeverage_Ratio(Double leverage_Ratio) {
        Leverage_Ratio = leverage_Ratio;
    }

    public Double getEquity_Multiplier() {
        return Equity_Multiplier;
    }

    public void setEquity_Multiplier(Double equity_Multiplier) {
        Equity_Multiplier = equity_Multiplier;
    }

    public Double getTier1_Capital_Ratio() {
        return Tier1_Capital_Ratio;
    }

    public void setTier1_Capital_Ratio(Double tier1_Capital_Ratio) {
        Tier1_Capital_Ratio = tier1_Capital_Ratio;
    }

    public Double getInventory_Turnover() {
        return Inventory_Turnover;
    }

    public void setInventory_Turnover(Double inventory_Turnover) {
        Inventory_Turnover = inventory_Turnover;
    }

    public Double getDIO_days() {
        return DIO_days;
    }

    public void setDIO_days(Double DIO_days) {
        this.DIO_days = DIO_days;
    }

    public Double getReceivables_Turnover() {
        return Receivables_Turnover;
    }

    public void setReceivables_Turnover(Double receivables_Turnover) {
        Receivables_Turnover = receivables_Turnover;
    }

    public Double getDSO_days() {
        return DSO_days;
    }

    public void setDSO_days(Double DSO_days) {
        this.DSO_days = DSO_days;
    }

    public Double getPayables_Turnover() {
        return Payables_Turnover;
    }

    public void setPayables_Turnover(Double payables_Turnover) {
        Payables_Turnover = payables_Turnover;
    }

    public Double getDPO_days() {
        return DPO_days;
    }

    public void setDPO_days(Double DPO_days) {
        this.DPO_days = DPO_days;
    }

    public Double getCCC_days() {
        return CCC_days;
    }

    public void setCCC_days(Double CCC_days) {
        this.CCC_days = CCC_days;
    }

    public Double getAsset_Turnover() {
        return Asset_Turnover;
    }

    public void setAsset_Turnover(Double asset_Turnover) {
        Asset_Turnover = asset_Turnover;
    }

    public Double getFixed_Asset_Turnover() {
        return Fixed_Asset_Turnover;
    }

    public void setFixed_Asset_Turnover(Double fixed_Asset_Turnover) {
        Fixed_Asset_Turnover = fixed_Asset_Turnover;
    }

    public Double getWorking_Capital_Turnover() {
        return Working_Capital_Turnover;
    }

    public void setWorking_Capital_Turnover(Double working_Capital_Turnover) {
        Working_Capital_Turnover = working_Capital_Turnover;
    }

    public Double getEquity_Turnover() {
        return Equity_Turnover;
    }

    public void setEquity_Turnover(Double equity_Turnover) {
        Equity_Turnover = equity_Turnover;
    }

    public Double getRevenue_Growth_YoY() {
        return Revenue_Growth_YoY;
    }

    public void setRevenue_Growth_YoY(Double revenue_Growth_YoY) {
        Revenue_Growth_YoY = revenue_Growth_YoY;
    }

    public Double getRevenue_Growth_3Y_CAGR() {
        return Revenue_Growth_3Y_CAGR;
    }

    public void setRevenue_Growth_3Y_CAGR(Double revenue_Growth_3Y_CAGR) {
        Revenue_Growth_3Y_CAGR = revenue_Growth_3Y_CAGR;
    }

    public Double getRevenue_Growth_5Y_CAGR() {
        return Revenue_Growth_5Y_CAGR;
    }

    public void setRevenue_Growth_5Y_CAGR(Double revenue_Growth_5Y_CAGR) {
        Revenue_Growth_5Y_CAGR = revenue_Growth_5Y_CAGR;
    }

    public Double getEBITDA_Growth_YoY() {
        return EBITDA_Growth_YoY;
    }

    public void setEBITDA_Growth_YoY(Double EBITDA_Growth_YoY) {
        this.EBITDA_Growth_YoY = EBITDA_Growth_YoY;
    }

    public Double getEBITDA_Growth_3Y_CAGR() {
        return EBITDA_Growth_3Y_CAGR;
    }

    public void setEBITDA_Growth_3Y_CAGR(Double EBITDA_Growth_3Y_CAGR) {
        this.EBITDA_Growth_3Y_CAGR = EBITDA_Growth_3Y_CAGR;
    }

    public Double getEBITDA_Growth_5Y_CAGR() {
        return EBITDA_Growth_5Y_CAGR;
    }

    public void setEBITDA_Growth_5Y_CAGR(Double EBITDA_Growth_5Y_CAGR) {
        this.EBITDA_Growth_5Y_CAGR = EBITDA_Growth_5Y_CAGR;
    }

    public Double getEBIT_Growth_YoY() {
        return EBIT_Growth_YoY;
    }

    public void setEBIT_Growth_YoY(Double EBIT_Growth_YoY) {
        this.EBIT_Growth_YoY = EBIT_Growth_YoY;
    }

    public Double getEBIT_Growth_3Y_CAGR() {
        return EBIT_Growth_3Y_CAGR;
    }

    public void setEBIT_Growth_3Y_CAGR(Double EBIT_Growth_3Y_CAGR) {
        this.EBIT_Growth_3Y_CAGR = EBIT_Growth_3Y_CAGR;
    }

    public Double getEBIT_Growth_5Y_CAGR() {
        return EBIT_Growth_5Y_CAGR;
    }

    public void setEBIT_Growth_5Y_CAGR(Double EBIT_Growth_5Y_CAGR) {
        this.EBIT_Growth_5Y_CAGR = EBIT_Growth_5Y_CAGR;
    }

    public Double getEPS_Growth_YoY() {
        return EPS_Growth_YoY;
    }

    public void setEPS_Growth_YoY(Double EPS_Growth_YoY) {
        this.EPS_Growth_YoY = EPS_Growth_YoY;
    }

    public Double getEPS_Growth_3Y_CAGR() {
        return EPS_Growth_3Y_CAGR;
    }

    public void setEPS_Growth_3Y_CAGR(Double EPS_Growth_3Y_CAGR) {
        this.EPS_Growth_3Y_CAGR = EPS_Growth_3Y_CAGR;
    }

    public Double getEPS_Growth_5Y_CAGR() {
        return EPS_Growth_5Y_CAGR;
    }

    public void setEPS_Growth_5Y_CAGR(Double EPS_Growth_5Y_CAGR) {
        this.EPS_Growth_5Y_CAGR = EPS_Growth_5Y_CAGR;
    }

    public Double getFCF_Growth_YoY() {
        return FCF_Growth_YoY;
    }

    public void setFCF_Growth_YoY(Double FCF_Growth_YoY) {
        this.FCF_Growth_YoY = FCF_Growth_YoY;
    }

    public Double getFCF_Growth_3Y_CAGR() {
        return FCF_Growth_3Y_CAGR;
    }

    public void setFCF_Growth_3Y_CAGR(Double FCF_Growth_3Y_CAGR) {
        this.FCF_Growth_3Y_CAGR = FCF_Growth_3Y_CAGR;
    }

    public Double getFCF_Growth_5Y_CAGR() {
        return FCF_Growth_5Y_CAGR;
    }

    public void setFCF_Growth_5Y_CAGR(Double FCF_Growth_5Y_CAGR) {
        this.FCF_Growth_5Y_CAGR = FCF_Growth_5Y_CAGR;
    }

    public Double getBVPS_Growth_YoY() {
        return BVPS_Growth_YoY;
    }

    public void setBVPS_Growth_YoY(Double BVPS_Growth_YoY) {
        this.BVPS_Growth_YoY = BVPS_Growth_YoY;
    }

    public Double getBVPS_Growth_3Y_CAGR() {
        return BVPS_Growth_3Y_CAGR;
    }

    public void setBVPS_Growth_3Y_CAGR(Double BVPS_Growth_3Y_CAGR) {
        this.BVPS_Growth_3Y_CAGR = BVPS_Growth_3Y_CAGR;
    }

    public Double getBVPS_Growth_5Y_CAGR() {
        return BVPS_Growth_5Y_CAGR;
    }

    public void setBVPS_Growth_5Y_CAGR(Double BVPS_Growth_5Y_CAGR) {
        this.BVPS_Growth_5Y_CAGR = BVPS_Growth_5Y_CAGR;
    }

    public Double getSales_Per_Share_Growth_YoY() {
        return Sales_Per_Share_Growth_YoY;
    }

    public void setSales_Per_Share_Growth_YoY(Double sales_Per_Share_Growth_YoY) {
        Sales_Per_Share_Growth_YoY = sales_Per_Share_Growth_YoY;
    }

    public Double getSales_Per_Share_Growth_3Y_CAGR() {
        return Sales_Per_Share_Growth_3Y_CAGR;
    }

    public void setSales_Per_Share_Growth_3Y_CAGR(Double sales_Per_Share_Growth_3Y_CAGR) {
        Sales_Per_Share_Growth_3Y_CAGR = sales_Per_Share_Growth_3Y_CAGR;
    }

    public Double getSales_Per_Share_Growth_5Y_CAGR() {
        return Sales_Per_Share_Growth_5Y_CAGR;
    }

    public void setSales_Per_Share_Growth_5Y_CAGR(Double sales_Per_Share_Growth_5Y_CAGR) {
        Sales_Per_Share_Growth_5Y_CAGR = sales_Per_Share_Growth_5Y_CAGR;
    }

    public Double getDPS() {
        return DPS;
    }

    public void setDPS(Double DPS) {
        this.DPS = DPS;
    }

    public Double getDPR_Payout_Ratio() {
        return DPR_Payout_Ratio;
    }

    public void setDPR_Payout_Ratio(Double DPR_Payout_Ratio) {
        this.DPR_Payout_Ratio = DPR_Payout_Ratio;
    }

    public Double getRetention_Ratio() {
        return Retention_Ratio;
    }

    public void setRetention_Ratio(Double retention_Ratio) {
        Retention_Ratio = retention_Ratio;
    }

    public Double getSustainable_Growth_Rate() {
        return Sustainable_Growth_Rate;
    }

    public void setSustainable_Growth_Rate(Double sustainable_Growth_Rate) {
        Sustainable_Growth_Rate = sustainable_Growth_Rate;
    }

    public Double getDividend_Coverage_Ratio() {
        return Dividend_Coverage_Ratio;
    }

    public void setDividend_Coverage_Ratio(Double dividend_Coverage_Ratio) {
        Dividend_Coverage_Ratio = dividend_Coverage_Ratio;
    }

    public Double getDividend_Yield() {
        return Dividend_Yield;
    }

    public void setDividend_Yield(Double dividend_Yield) {
        Dividend_Yield = dividend_Yield;
    }

    public Double getFCF_Yield() {
        return FCF_Yield;
    }

    public void setFCF_Yield(Double FCF_Yield) {
        this.FCF_Yield = FCF_Yield;
    }

    public Double getEarnings_Yield() {
        return Earnings_Yield;
    }

    public void setEarnings_Yield(Double earnings_Yield) {
        Earnings_Yield = earnings_Yield;
    }

    public Double getBuyback_Yield() {
        return Buyback_Yield;
    }

    public void setBuyback_Yield(Double buyback_Yield) {
        Buyback_Yield = buyback_Yield;
    }

    public Double getTotal_Shareholder_Yield() {
        return Total_Shareholder_Yield;
    }

    public void setTotal_Shareholder_Yield(Double total_Shareholder_Yield) {
        Total_Shareholder_Yield = total_Shareholder_Yield;
    }

    public Double getGraham_Number() {
        return Graham_Number;
    }

    public void setGraham_Number(Double graham_Number) {
        Graham_Number = graham_Number;
    }

    public Double getBen_Graham_Formula() {
        return Ben_Graham_Formula;
    }

    public void setBen_Graham_Formula(Double ben_Graham_Formula) {
        Ben_Graham_Formula = ben_Graham_Formula;
    }

    public Double getPeter_Lynch_Fair_Value() {
        return Peter_Lynch_Fair_Value;
    }

    public void setPeter_Lynch_Fair_Value(Double peter_Lynch_Fair_Value) {
        Peter_Lynch_Fair_Value = peter_Lynch_Fair_Value;
    }

    public Double getAltman_Z_Score() {
        return Altman_Z_Score;
    }

    public void setAltman_Z_Score(Double altman_Z_Score) {
        Altman_Z_Score = altman_Z_Score;
    }

    public Double getPiotroski_F_Score() {
        return Piotroski_F_Score;
    }

    public void setPiotroski_F_Score(Double piotroski_F_Score) {
        Piotroski_F_Score = piotroski_F_Score;
    }

    public Double getBeta() {
        return Beta;
    }

    public void setBeta(Double beta) {
        Beta = beta;
    }

    public Double getAlpha() {
        return Alpha;
    }

    public void setAlpha(Double alpha) {
        Alpha = alpha;
    }

    public Double getR_Squared() {
        return R_Squared;
    }

    public void setR_Squared(Double r_Squared) {
        R_Squared = r_Squared;
    }

    public Double getSharpe_Ratio() {
        return Sharpe_Ratio;
    }

    public void setSharpe_Ratio(Double sharpe_Ratio) {
        Sharpe_Ratio = sharpe_Ratio;
    }

    public Double getTreynor_Ratio() {
        return Treynor_Ratio;
    }

    public void setTreynor_Ratio(Double treynor_Ratio) {
        Treynor_Ratio = treynor_Ratio;
    }

    public Double getSortino_Ratio() {
        return Sortino_Ratio;
    }

    public void setSortino_Ratio(Double sortino_Ratio) {
        Sortino_Ratio = sortino_Ratio;
    }

    public Double getStandard_Deviation() {
        return Standard_Deviation;
    }

    public void setStandard_Deviation(Double standard_Deviation) {
        Standard_Deviation = standard_Deviation;
    }

    public Double getValue_at_Risk_VaR() {
        return Value_at_Risk_VaR;
    }

    public void setValue_at_Risk_VaR(Double value_at_Risk_VaR) {
        Value_at_Risk_VaR = value_at_Risk_VaR;
    }

    public Double getMaximum_Drawdown() {
        return Maximum_Drawdown;
    }

    public void setMaximum_Drawdown(Double maximum_Drawdown) {
        Maximum_Drawdown = maximum_Drawdown;
    }
}