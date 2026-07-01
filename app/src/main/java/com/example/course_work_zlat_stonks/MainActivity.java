package com.example.course_work_zlat_stonks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FinancialMetricsDao dao;
    private final Map<String, List<String>> categoryColumns = new HashMap<>();
    private final Map<String, Integer> recyclerViewIds = new HashMap<>();
    private UserPreferences userPrefs;
    private LinearLayout navHome, navSettings, navLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userPrefs = new UserPreferences(this);
        if (!userPrefs.isDarkMode()) {
            setTheme(R.style.AppTheme_Light);
        } else {
            setTheme(R.style.AppTheme_Dark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!userPrefs.isLoggedIn()) {
            goToLogin();
            return;
        }

        setupCategoryMappings();
        setupNavigation();

        AppDatabase db = AppDatabase.getInstance(this);
        dao = db.financialMetricsDao();

        setupUI();

        showGreeting();

        if (userPrefs.isGuest()) {
            showGuestModeToast();
        }
    }

    private void applyTheme() {
        if (userPrefs != null && !userPrefs.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    private void setupNavigation() {
        navHome = findViewById(R.id. nav_home);
        navSettings = findViewById(R.id.nav_settings);
        navLogout = findViewById(R.id.nav_logout);

        TextView tvHome = findViewById(R.id.tv_home);
        if (tvHome != null) tvHome.setTextColor(getColor(R.color.gold));

        navHome.setOnClickListener(v -> {
        });

        navSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        navLogout.setOnClickListener(v -> logout());
    }

    private void logout() {
        userPrefs.logout();
        goToLogin();
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void showGreeting() {
        TextView header = findViewById(R.id.mults);
        if (userPrefs.isGuest()) {
            header.setText("🎭 Добро пожаловать, Доступен просмотр основных показателей");
        } else {
            header.setText("👋 Добро пожаловать, " + userPrefs.getUsername() + "!");
        }
    }

    private void showGuestModeToast() {
        Toast.makeText(this, "🎭 Гостевой режим: доступны только основные показатели", Toast.LENGTH_LONG).show();
    }

    private void setupUI() {
        EditText tickerInput = findViewById(R.id.mult_enter);
        Button searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(v -> {
            String ticker = tickerInput.getText().toString().trim().toUpperCase();
            if (!ticker.isEmpty()) {
                searchTicker(ticker);
            }
        });
    }

    private void searchTicker(String ticker) {
        TextView header = findViewById(R.id.mults);
        header.setText("📊 Загрузка данных для " + ticker + "...");

        new Thread(() -> {
            FinancialMetricsEntity entity = dao.getByTicker(ticker);
            runOnUiThread(() -> {
                if (entity != null) {
                    loadDataToCategories(entity);
                    header.setText("📊 Мультипликаторы для " + ticker + ":");
                } else {
                    header.setText("❌ Тикер " + ticker + " не найден в базе");
                }
            });
        }).start();
    }

    private void loadDataToCategories(FinancialMetricsEntity entity) {
        if (userPrefs.isGuest()) {
            List<StateAdapter.Metric> metrics = new ArrayList<>();
            metrics.add(new StateAdapter.Metric("📌 Тикер", entity.getTicker()));
            metrics.add(new StateAdapter.Metric("💰 Рыночная капитализация", formatDoubleValue(entity.getMarket_Cap())));
            metrics.add(new StateAdapter.Metric("🏢 EV", formatDoubleValue(entity.getEnterprise_Value())));
            metrics.add(new StateAdapter.Metric("📊 P/E", formatDoubleValue(entity.getPE_Trailing())));
            metrics.add(new StateAdapter.Metric("📊 P/S", formatDoubleValue(entity.getPS())));
            showMetricsInCategory("basic", metrics);
        } else {
            for (Map.Entry<String, List<String>> entry : categoryColumns.entrySet()) {
                String categoryKey = entry.getKey();
                List<String> columns = entry.getValue();
                List<StateAdapter.Metric> metrics = new ArrayList<>();

                for (String column : columns) {
                    String value = getValueFromEntity(entity, column);
                    if (value != null && !value.equals("—") && !value.isEmpty()) {
                        String name = formatMetricName(column);
                        metrics.add(new StateAdapter.Metric(name, value));
                    }
                }
                showMetricsInCategory(categoryKey, metrics);
            }
        }
    }
    private void setupCategoryMappings() {
        // Основные показатели
        List<String> basicColumns = new ArrayList<>();
        basicColumns.add("Ticker");
        basicColumns.add("Market_Cap");
        basicColumns.add("Enterprise_Value");
        categoryColumns.put("basic", basicColumns);
        recyclerViewIds.put("basic", R.id.recyclerViewBasic);

        // Рыночные мультипликаторы
        List<String> marketColumns = new ArrayList<>();
        marketColumns.add("PE_Trailing");
        marketColumns.add("PE_Forward");
        marketColumns.add("PEG_Ratio");
        marketColumns.add("PBV");
        marketColumns.add("PS");
        marketColumns.add("PCF_TTM");
        marketColumns.add("PFCF");
        marketColumns.add("PE_Relative");
        marketColumns.add("Shiller_PE");
        marketColumns.add("PTBV");
        marketColumns.add("PEBIT");
        marketColumns.add("PEBITDA");
        marketColumns.add("PEGY");
        categoryColumns.put("market", marketColumns);
        recyclerViewIds.put("market", R.id.recyclerViewMarket);

        // EV мультипликаторы
        List<String> evColumns = new ArrayList<>();
        evColumns.add("EV_Sales");
        evColumns.add("EV_EBITDA");
        evColumns.add("EV_EBIT");
        evColumns.add("EV_GP");
        evColumns.add("EV_Invested_Capital");
        evColumns.add("EV_FCF");
        evColumns.add("EV_Total_Assets");
        categoryColumns.put("ev", evColumns);
        recyclerViewIds.put("ev", R.id.recyclerViewCap);

        // Маржинальность
        List<String> marginColumns = new ArrayList<>();
        marginColumns.add("Gross_Margin");
        marginColumns.add("Operating_Margin_ROS");
        marginColumns.add("Net_Profit_Margin");
        marginColumns.add("Pretax_Margin");
        marginColumns.add("EBITDA_Margin");
        marginColumns.add("EBIT_Margin");
        marginColumns.add("FCF_Margin");
        categoryColumns.put("margins", marginColumns);
        recyclerViewIds.put("margins", R.id.recyclerViewProfEff);

        // Рентабельность
        List<String> returnColumns = new ArrayList<>();
        returnColumns.add("ROA");
        returnColumns.add("ROAA");
        returnColumns.add("ROE");
        returnColumns.add("ROACE");
        returnColumns.add("ROIC");
        returnColumns.add("ROCE");
        returnColumns.add("ROE_DuPont_3");
        returnColumns.add("ROE_DuPont_5");
        returnColumns.add("CFROI");
        returnColumns.add("CROCI");
        returnColumns.add("RONA");
        returnColumns.add("ROTC");
        returnColumns.add("ROIC_Pretax");
        categoryColumns.put("returns", returnColumns);
        recyclerViewIds.put("returns", R.id.recyclerViewGrYieldCap);

        // Эффективность менеджмента
        List<String> managementColumns = new ArrayList<>();
        managementColumns.add("SG_and_A_to_Sales");
        managementColumns.add("R_and_D_to_Sales");
        managementColumns.add("Capex_to_Sales");
        managementColumns.add("Capex_to_Depreciation");
        managementColumns.add("Operating_Leverage");
        managementColumns.add("Financial_Leverage");
        categoryColumns.put("management", managementColumns);
        recyclerViewIds.put("management", R.id.recyclerViewManag);

        // Ликвидность
        List<String> liquidityColumns = new ArrayList<>();
        liquidityColumns.add("Current_Ratio");
        liquidityColumns.add("Quick_Ratio");
        liquidityColumns.add("Cash_Ratio");
        liquidityColumns.add("Operating_Cash_Flow_Ratio");
        liquidityColumns.add("Defensive_Interval_Ratio");
        categoryColumns.put("liquidity", liquidityColumns);
        recyclerViewIds.put("liquidity", R.id.recyclerViewLiq);

        // Долговая нагрузка
        List<String> debtColumns = new ArrayList<>();
        debtColumns.add("Debt_Equity");
        debtColumns.add("Debt_Assets");
        debtColumns.add("Debt_Capital");
        debtColumns.add("Net_Debt_Equity");
        debtColumns.add("Net_Debt_EBITDA");
        debtColumns.add("Net_Debt_EBIT");
        debtColumns.add("Gross_Debt_EBITDA");
        debtColumns.add("EBITDA_Interest");
        debtColumns.add("ICR");
        debtColumns.add("Fixed_Charge_Coverage");
        debtColumns.add("DSCR");
        debtColumns.add("Leverage_Ratio");
        debtColumns.add("Equity_Multiplier");
        debtColumns.add("Tier1_Capital_Ratio");
        categoryColumns.put("debt", debtColumns);
        recyclerViewIds.put("debt", R.id.recyclerViewDebtLoad);

        // Оборачиваемость
        List<String> turnoverColumns = new ArrayList<>();
        turnoverColumns.add("Inventory_Turnover");
        turnoverColumns.add("DIO_days");
        turnoverColumns.add("Receivables_Turnover");
        turnoverColumns.add("DSO_days");
        turnoverColumns.add("Payables_Turnover");
        turnoverColumns.add("DPO_days");
        turnoverColumns.add("CCC_days");
        turnoverColumns.add("Asset_Turnover");
        turnoverColumns.add("Fixed_Asset_Turnover");
        turnoverColumns.add("Working_Capital_Turnover");
        turnoverColumns.add("Equity_Turnover");
        categoryColumns.put("turnover", turnoverColumns);
        recyclerViewIds.put("turnover", R.id.recyclerViewBusinesTurn);

        // Рост
        List<String> growthColumns = new ArrayList<>();
        growthColumns.add("Revenue_Growth_YoY");
        growthColumns.add("Revenue_Growth_3Y_CAGR");
        growthColumns.add("Revenue_Growth_5Y_CAGR");
        growthColumns.add("EBITDA_Growth_YoY");
        growthColumns.add("EBITDA_Growth_3Y_CAGR");
        growthColumns.add("EBITDA_Growth_5Y_CAGR");
        growthColumns.add("EBIT_Growth_YoY");
        growthColumns.add("EBIT_Growth_3Y_CAGR");
        growthColumns.add("EBIT_Growth_5Y_CAGR");
        growthColumns.add("EPS_Growth_YoY");
        growthColumns.add("EPS_Growth_3Y_CAGR");
        growthColumns.add("EPS_Growth_5Y_CAGR");
        growthColumns.add("FCF_Growth_YoY");
        growthColumns.add("FCF_Growth_3Y_CAGR");
        growthColumns.add("FCF_Growth_5Y_CAGR");
        growthColumns.add("BVPS_Growth_YoY");
        growthColumns.add("BVPS_Growth_3Y_CAGR");
        growthColumns.add("BVPS_Growth_5Y_CAGR");
        growthColumns.add("Sales_Per_Share_Growth_YoY");
        growthColumns.add("Sales_Per_Share_Growth_3Y_CAGR");
        growthColumns.add("Sales_Per_Share_Growth_5Y_CAGR");
        categoryColumns.put("growth", growthColumns);
        recyclerViewIds.put("growth", R.id.recyclerViewGrowth);

        // Дивиденды
        List<String> dividendColumns = new ArrayList<>();
        dividendColumns.add("DPS");
        dividendColumns.add("DPR_Payout_Ratio");
        dividendColumns.add("Retention_Ratio");
        dividendColumns.add("Sustainable_Growth_Rate");
        dividendColumns.add("Dividend_Coverage_Ratio");
        dividendColumns.add("Dividend_Yield");
        categoryColumns.put("dividend", dividendColumns);
        recyclerViewIds.put("dividend", R.id.recyclerViewDiv);

        // Доходность
        List<String> yieldColumns = new ArrayList<>();
        yieldColumns.add("FCF_Yield");
        yieldColumns.add("Earnings_Yield");
        yieldColumns.add("Buyback_Yield");
        yieldColumns.add("Total_Shareholder_Yield");
        categoryColumns.put("yield", yieldColumns);
        recyclerViewIds.put("yield", R.id.recyclerViewYield);

        // Оценочные модели
        List<String> modelColumns = new ArrayList<>();
        modelColumns.add("Graham_Number");
        modelColumns.add("Ben_Graham_Formula");
        modelColumns.add("Peter_Lynch_Fair_Value");
        modelColumns.add("Altman_Z_Score");
        modelColumns.add("Piotroski_F_Score");
        categoryColumns.put("models", modelColumns);
        recyclerViewIds.put("models", R.id.recyclerViewCompEvalMod);

        // Риски
        List<String> riskColumns = new ArrayList<>();
        riskColumns.add("Beta");
        riskColumns.add("Alpha");
        riskColumns.add("R_Squared");
        riskColumns.add("Sharpe_Ratio");
        riskColumns.add("Treynor_Ratio");
        riskColumns.add("Sortino_Ratio");
        riskColumns.add("Standard_Deviation");
        riskColumns.add("Value_at_Risk_VaR");
        riskColumns.add("Maximum_Drawdown");
        categoryColumns.put("risk", riskColumns);
        recyclerViewIds.put("risk", R.id.recyclerViewRisk);
    }
    private String getValueFromEntity(FinancialMetricsEntity entity, String columnName) {

        switch (columnName) {
            // Основные
            case "Ticker": return entity.getTicker();
            case "Market_Cap": return formatDoubleValue(entity.getMarket_Cap());
            case "Enterprise_Value": return formatDoubleValue(entity.getEnterprise_Value());

            // Рыночные мультипликаторы
            case "PE_Trailing": return formatDoubleValue(entity.getPE_Trailing());
            case "PE_Forward": return formatDoubleValue(entity.getPE_Forward());
            case "PEG_Ratio": return formatDoubleValue(entity.getPEG_Ratio());
            case "PBV": return formatDoubleValue(entity.getPBV());
            case "PS": return formatDoubleValue(entity.getPS());
            case "PCF_TTM": return formatDoubleValue(entity.getPCF_TTM());
            case "PFCF": return formatDoubleValue(entity.getPFCF());
            case "PE_Relative": return formatDoubleValue(entity.getPE_Relative());
            case "Shiller_PE": return formatDoubleValue(entity.getShiller_PE());
            case "PTBV": return formatDoubleValue(entity.getPTBV());
            case "PEBIT": return formatDoubleValue(entity.getPEBIT());
            case "PEBITDA": return formatDoubleValue(entity.getPEBITDA());
            case "PEGY": return formatDoubleValue(entity.getPEGY());

            // EV мультипликаторы
            case "EV_Sales": return formatDoubleValue(entity.getEV_Sales());
            case "EV_EBITDA": return formatDoubleValue(entity.getEV_EBITDA());
            case "EV_EBIT": return formatDoubleValue(entity.getEV_EBIT());
            case "EV_GP": return formatDoubleValue(entity.getEV_GP());
            case "EV_Invested_Capital": return formatDoubleValue(entity.getEV_Invested_Capital());
            case "EV_FCF": return formatDoubleValue(entity.getEV_FCF());
            case "EV_Total_Assets": return formatDoubleValue(entity.getEV_Total_Assets());

            // Маржинальность (проценты)
            case "Gross_Margin": return formatPercentValue(entity.getGross_Margin());
            case "Operating_Margin_ROS": return formatPercentValue(entity.getOperating_Margin_ROS());
            case "Net_Profit_Margin": return formatPercentValue(entity.getNet_Profit_Margin());
            case "Pretax_Margin": return formatPercentValue(entity.getPretax_Margin());
            case "EBITDA_Margin": return formatPercentValue(entity.getEBITDA_Margin());
            case "EBIT_Margin": return formatPercentValue(entity.getEBIT_Margin());
            case "FCF_Margin": return formatPercentValue(entity.getFCF_Margin());

            // Рентабельность (проценты)
            case "ROA": return formatPercentValue(entity.getROA());
            case "ROAA": return formatPercentValue(entity.getROAA());
            case "ROE": return formatPercentValue(entity.getROE());
            case "ROACE": return formatPercentValue(entity.getROACE());
            case "ROIC": return formatPercentValue(entity.getROIC());
            case "ROCE": return formatPercentValue(entity.getROCE());
            case "ROE_DuPont_3": return formatPercentValue(entity.getROE_DuPont_3());
            case "ROE_DuPont_5": return formatPercentValue(entity.getROE_DuPont_5());
            case "CFROI": return formatPercentValue(entity.getCFROI());
            case "CROCI": return formatPercentValue(entity.getCROCI());
            case "RONA": return formatPercentValue(entity.getRONA());
            case "ROTC": return formatPercentValue(entity.getROTC());
            case "ROIC_Pretax": return formatPercentValue(entity.getROIC_Pretax());

            // Эффективность (SG&A, R&D - проценты; остальное - числа)
            case "SG_and_A_to_Sales": return formatPercentValue(entity.getSG_and_A_to_Sales());
            case "R_and_D_to_Sales": return formatPercentValue(entity.getR_and_D_to_Sales());
            case "Capex_to_Sales": return formatPercentValue(entity.getCapex_to_Sales());
            case "Capex_to_Depreciation": return formatDoubleValue(entity.getCapex_to_Depreciation());
            case "Operating_Leverage": return formatDoubleValue(entity.getOperating_Leverage());
            case "Financial_Leverage": return formatDoubleValue(entity.getFinancial_Leverage());

            // Ликвидность (числа)
            case "Current_Ratio": return formatDoubleValue(entity.getCurrent_Ratio());
            case "Quick_Ratio": return formatDoubleValue(entity.getQuick_Ratio());
            case "Cash_Ratio": return formatDoubleValue(entity.getCash_Ratio());
            case "Operating_Cash_Flow_Ratio": return formatDoubleValue(entity.getOperating_Cash_Flow_Ratio());
            case "Defensive_Interval_Ratio": return formatDoubleValue(entity.getDefensive_Interval_Ratio());

            // Долговая нагрузка (смешанные типы)
            case "Debt_Equity": return formatDoubleValue(entity.getDebt_Equity());
            case "Debt_Assets": return formatPercentValue(entity.getDebt_Assets());
            case "Debt_Capital": return formatPercentValue(entity.getDebt_Capital());
            case "Net_Debt_Equity": return formatDoubleValue(entity.getNet_Debt_Equity());
            case "Net_Debt_EBITDA": return formatDoubleValue(entity.getNet_Debt_EBITDA());
            case "Net_Debt_EBIT": return formatDoubleValue(entity.getNet_Debt_EBIT());
            case "Gross_Debt_EBITDA": return formatDoubleValue(entity.getGross_Debt_EBITDA());
            case "EBITDA_Interest": return formatDoubleValue(entity.getEBITDA_Interest());
            case "ICR": return formatDoubleValue(entity.getICR());
            case "Fixed_Charge_Coverage": return formatDoubleValue(entity.getFixed_Charge_Coverage());
            case "DSCR": return formatDoubleValue(entity.getDSCR());
            case "Leverage_Ratio": return formatDoubleValue(entity.getLeverage_Ratio());
            case "Equity_Multiplier": return formatDoubleValue(entity.getEquity_Multiplier());
            case "Tier1_Capital_Ratio": return formatPercentValue(entity.getTier1_Capital_Ratio());

            // Оборачиваемость (числа и дни)
            case "Inventory_Turnover": return formatDoubleValue(entity.getInventory_Turnover());
            case "DIO_days": return formatDoubleValue(entity.getDIO_days());
            case "Receivables_Turnover": return formatDoubleValue(entity.getReceivables_Turnover());
            case "DSO_days": return formatDoubleValue(entity.getDSO_days());
            case "Payables_Turnover": return formatDoubleValue(entity.getPayables_Turnover());
            case "DPO_days": return formatDoubleValue(entity.getDPO_days());
            case "CCC_days": return formatDoubleValue(entity.getCCC_days());
            case "Asset_Turnover": return formatDoubleValue(entity.getAsset_Turnover());
            case "Fixed_Asset_Turnover": return formatDoubleValue(entity.getFixed_Asset_Turnover());
            case "Working_Capital_Turnover": return formatDoubleValue(entity.getWorking_Capital_Turnover());
            case "Equity_Turnover": return formatDoubleValue(entity.getEquity_Turnover());

            // Рост (проценты)
            case "Revenue_Growth_YoY": return formatPercentValue(entity.getRevenue_Growth_YoY());
            case "Revenue_Growth_3Y_CAGR": return formatPercentValue(entity.getRevenue_Growth_3Y_CAGR());
            case "Revenue_Growth_5Y_CAGR": return formatPercentValue(entity.getRevenue_Growth_5Y_CAGR());
            case "EBITDA_Growth_YoY": return formatPercentValue(entity.getEBITDA_Growth_YoY());
            case "EBITDA_Growth_3Y_CAGR": return formatPercentValue(entity.getEBITDA_Growth_3Y_CAGR());
            case "EBITDA_Growth_5Y_CAGR": return formatPercentValue(entity.getEBITDA_Growth_5Y_CAGR());
            case "EBIT_Growth_YoY": return formatPercentValue(entity.getEBIT_Growth_YoY());
            case "EBIT_Growth_3Y_CAGR": return formatPercentValue(entity.getEBIT_Growth_3Y_CAGR());
            case "EBIT_Growth_5Y_CAGR": return formatPercentValue(entity.getEBIT_Growth_5Y_CAGR());
            case "EPS_Growth_YoY": return formatPercentValue(entity.getEPS_Growth_YoY());
            case "EPS_Growth_3Y_CAGR": return formatPercentValue(entity.getEPS_Growth_3Y_CAGR());
            case "EPS_Growth_5Y_CAGR": return formatPercentValue(entity.getEPS_Growth_5Y_CAGR());
            case "FCF_Growth_YoY": return formatPercentValue(entity.getFCF_Growth_YoY());
            case "FCF_Growth_3Y_CAGR": return formatPercentValue(entity.getFCF_Growth_3Y_CAGR());
            case "FCF_Growth_5Y_CAGR": return formatPercentValue(entity.getFCF_Growth_5Y_CAGR());
            case "BVPS_Growth_YoY": return formatPercentValue(entity.getBVPS_Growth_YoY());
            case "BVPS_Growth_3Y_CAGR": return formatPercentValue(entity.getBVPS_Growth_3Y_CAGR());
            case "BVPS_Growth_5Y_CAGR": return formatPercentValue(entity.getBVPS_Growth_5Y_CAGR());
            case "Sales_Per_Share_Growth_YoY": return formatPercentValue(entity.getSales_Per_Share_Growth_YoY());
            case "Sales_Per_Share_Growth_3Y_CAGR": return formatPercentValue(entity.getSales_Per_Share_Growth_3Y_CAGR());
            case "Sales_Per_Share_Growth_5Y_CAGR": return formatPercentValue(entity.getSales_Per_Share_Growth_5Y_CAGR());

            // Дивиденды
            case "DPS": return formatDoubleValue(entity.getDPS());
            case "DPR_Payout_Ratio": return formatPercentValue(entity.getDPR_Payout_Ratio());
            case "Retention_Ratio": return formatPercentValue(entity.getRetention_Ratio());
            case "Sustainable_Growth_Rate": return formatPercentValue(entity.getSustainable_Growth_Rate());
            case "Dividend_Coverage_Ratio": return formatDoubleValue(entity.getDividend_Coverage_Ratio());
            case "Dividend_Yield": return formatPercentValue(entity.getDividend_Yield());

            // Доходность (проценты)
            case "FCF_Yield": return formatPercentValue(entity.getFCF_Yield());
            case "Earnings_Yield": return formatPercentValue(entity.getEarnings_Yield());
            case "Buyback_Yield": return formatPercentValue(entity.getBuyback_Yield());
            case "Total_Shareholder_Yield": return formatPercentValue(entity.getTotal_Shareholder_Yield());

            // Оценочные модели (числа)
            case "Graham_Number": return formatDoubleValue(entity.getGraham_Number());
            case "Ben_Graham_Formula": return formatDoubleValue(entity.getBen_Graham_Formula());
            case "Peter_Lynch_Fair_Value": return formatDoubleValue(entity.getPeter_Lynch_Fair_Value());
            case "Altman_Z_Score": return formatDoubleValue(entity.getAltman_Z_Score());
            case "Piotroski_F_Score": return formatDoubleValue(entity.getPiotroski_F_Score());

            // Риски
            case "Beta": return formatDoubleValue(entity.getBeta());
            case "Alpha": return formatDoubleValue(entity.getAlpha());
            case "R_Squared": return formatPercentValue(entity.getR_Squared());
            case "Sharpe_Ratio": return formatDoubleValue(entity.getSharpe_Ratio());
            case "Treynor_Ratio": return formatDoubleValue(entity.getTreynor_Ratio());
            case "Sortino_Ratio": return formatDoubleValue(entity.getSortino_Ratio());
            case "Standard_Deviation": return formatPercentValue(entity.getStandard_Deviation());
            case "Value_at_Risk_VaR": return formatPercentValue(entity.getValue_at_Risk_VaR());
            case "Maximum_Drawdown": return formatPercentValue(entity.getMaximum_Drawdown());

            default: return "—";
        }
    }
    private String formatDoubleValue(Double value) {
        if (value == null) return "—";
        if (value == (long) value.doubleValue()) {
            return String.format("%,d", (long) value.doubleValue());
        } else {
            return String.format("%,.2f", value);
        }
    }

    private String formatPercentValue(Double value) {
        if (value == null) return "—";
        return String.format("%,.2f%%", value * 100);
    }

    private String formatMetricName(String columnName) {
        switch (columnName) {
            // ============ ОСНОВНЫЕ ПОКАЗАТЕЛИ ============
            case "Ticker": return "📌 Тикер";
            case "Market_Cap": return "💰 Рыночная капитализация";
            case "Enterprise_Value": return "🏢 Стоимость бизнеса (EV)";

            // ============ РЫНОЧНЫЕ МУЛЬТИПЛИКАТОРЫ ============
            case "PE_Trailing": return "📊 P/E (Trailing)";
            case "PE_Forward": return "🔮 P/E (Forward)";
            case "PEG_Ratio": return "📈 PEG Ratio";
            case "PBV": return "📘 P/BV (Price to Book)";
            case "PS": return "💵 P/S (Price to Sales)";
            case "PCF_TTM": return "💵 P/CF (Price to Cash Flow)";
            case "PFCF": return "💵 P/FCF (Price to Free Cash Flow)";
            case "PE_Relative": return "📊 P/E Relative";
            case "Shiller_PE": return "📊 Shiller P/E (CAPE)";
            case "PTBV": return "📘 P/TBV (Tangible Book Value)";
            case "PEBIT": return "📊 P/EBIT";
            case "PEBITDA": return "📊 P/EBITDA";
            case "PEGY": return "📈 PEGY Ratio";

            // ============ EV МУЛЬТИПЛИКАТОРЫ ============
            case "EV_Sales": return "🏢 EV/Sales";
            case "EV_EBITDA": return "🏢 EV/EBITDA";
            case "EV_EBIT": return "🏢 EV/EBIT";
            case "EV_GP": return "🏢 EV/Gross Profit";
            case "EV_Invested_Capital": return "🏢 EV/Invested Capital";
            case "EV_FCF": return "🏢 EV/FCF";
            case "EV_Total_Assets": return "🏢 EV/Total Assets";

            // ============ МАРЖИНАЛЬНОСТЬ ============
            case "Gross_Margin": return "📊 Валовая маржа";
            case "Operating_Margin_ROS": return "📊 Операционная маржа (ROS)";
            case "Net_Profit_Margin": return "💰 Чистая рентабельность";
            case "Pretax_Margin": return "💰 Прибыль до налогов";
            case "EBITDA_Margin": return "📊 EBITDA маржа";
            case "EBIT_Margin": return "📊 EBIT маржа";
            case "FCF_Margin": return "💵 FCF маржа";

            // ============ РЕНТАБЕЛЬНОСТЬ ============
            case "ROA": return "📊 ROA (Return on Assets)";
            case "ROAA": return "📊 ROAA (Return on Avg Assets)";
            case "ROE": return "📊 ROE (Return on Equity)";
            case "ROACE": return "📊 ROACE (Return on Avg Capital)";
            case "ROIC": return "📊 ROIC (Return on Invested Capital)";
            case "ROCE": return "📊 ROCE (Return on Capital Employed)";
            case "ROE_DuPont_3": return "📊 ROE (DuPont 3-factor)";
            case "ROE_DuPont_5": return "📊 ROE (DuPont 5-factor)";
            case "CFROI": return "💵 CFROI (Cash Flow ROI)";
            case "CROCI": return "💵 CROCI (Cash Return on Capital)";
            case "RONA": return "📊 RONA (Return on Net Assets)";
            case "ROTC": return "📊 ROTC (Return on Total Capital)";
            case "ROIC_Pretax": return "📊 ROIC (Pre-tax)";

            // ============ ЭФФЕКТИВНОСТЬ МЕНЕДЖМЕНТА ============
            case "SG_and_A_to_Sales": return "📊 SG&A к выручке";
            case "R_and_D_to_Sales": return "🔬 R&D к выручке";
            case "Capex_to_Sales": return "🏭 Capex к выручке";
            case "Capex_to_Depreciation": return "🏭 Capex к амортизации";
            case "Operating_Leverage": return "⚙️ Операционный рычаг";
            case "Financial_Leverage": return "⚖️ Финансовый рычаг";

            // ============ ЛИКВИДНОСТЬ ============
            case "Current_Ratio": return "💧 Текущая ликвидность";
            case "Quick_Ratio": return "💧 Быстрая ликвидность";
            case "Cash_Ratio": return "💵 Денежная ликвидность";
            case "Operating_Cash_Flow_Ratio": return "💵 Операционный денежный поток";
            case "Defensive_Interval_Ratio": return "🛡️ Защитный интервал (дни)";

            // ============ ДОЛГОВАЯ НАГРУЗКА ============
            case "Debt_Equity": return "⚖️ D/E (Долг/Собственный капитал)";
            case "Debt_Assets": return "⚖️ D/A (Долг/Активы)";
            case "Debt_Capital": return "⚖️ D/Capital (Долг/Капитал)";
            case "Net_Debt_Equity": return "⚖️ Net D/E (Чистый долг/Equity)";
            case "Net_Debt_EBITDA": return "⚖️ Net Debt/EBITDA";
            case "Net_Debt_EBIT": return "⚖️ Net Debt/EBIT";
            case "Gross_Debt_EBITDA": return "⚖️ Gross Debt/EBITDA";
            case "EBITDA_Interest": return "📊 EBITDA / Проценты";
            case "ICR": return "📊 ICR (Коэффициент покрытия процентов)";
            case "Fixed_Charge_Coverage": return "📊 Fixed Charge Coverage";
            case "DSCR": return "📊 DSCR (Обслуживание долга)";
            case "Leverage_Ratio": return "⚖️ Коэффициент левериджа";
            case "Equity_Multiplier": return "⚖️ Мультипликатор капитала";
            case "Tier1_Capital_Ratio": return "🏦 Tier 1 Capital Ratio";

            // ============ ОБОРАЧИВАЕМОСТЬ ============
            case "Inventory_Turnover": return "🔄 Оборачиваемость запасов";
            case "DIO_days": return "📅 DIO (Дни оборота запасов)";
            case "Receivables_Turnover": return "🔄 Оборачиваемость дебиторки";
            case "DSO_days": return "📅 DSO (Дни оборота дебиторки)";
            case "Payables_Turnover": return "🔄 Оборачиваемость кредиторки";
            case "DPO_days": return "📅 DPO (Дни оборота кредиторки)";
            case "CCC_days": return "🔄 CCC (Денежный цикл)";
            case "Asset_Turnover": return "🔄 Оборачиваемость активов";
            case "Fixed_Asset_Turnover": return "🔄 Оборачиваемость основных активов";
            case "Working_Capital_Turnover": return "🔄 Оборачиваемость оборотного капитала";
            case "Equity_Turnover": return "🔄 Оборачиваемость капитала";

            // ============ РОСТ ============
            case "Revenue_Growth_YoY": return "📈 Рост выручки (год к году)";
            case "Revenue_Growth_3Y_CAGR": return "📈 Рост выручки (3 года CAGR)";
            case "Revenue_Growth_5Y_CAGR": return "📈 Рост выручки (5 лет CAGR)";
            case "EBITDA_Growth_YoY": return "📈 Рост EBITDA (год к году)";
            case "EBITDA_Growth_3Y_CAGR": return "📈 Рост EBITDA (3 года CAGR)";
            case "EBITDA_Growth_5Y_CAGR": return "📈 Рост EBITDA (5 лет CAGR)";
            case "EBIT_Growth_YoY": return "📈 Рост EBIT (год к году)";
            case "EBIT_Growth_3Y_CAGR": return "📈 Рост EBIT (3 года CAGR)";
            case "EBIT_Growth_5Y_CAGR": return "📈 Рост EBIT (5 лет CAGR)";
            case "EPS_Growth_YoY": return "📈 Рост EPS (год к году)";
            case "EPS_Growth_3Y_CAGR": return "📈 Рост EPS (3 года CAGR)";
            case "EPS_Growth_5Y_CAGR": return "📈 Рост EPS (5 лет CAGR)";
            case "FCF_Growth_YoY": return "📈 Рост FCF (год к году)";
            case "FCF_Growth_3Y_CAGR": return "📈 Рост FCF (3 года CAGR)";
            case "FCF_Growth_5Y_CAGR": return "📈 Рост FCF (5 лет CAGR)";
            case "BVPS_Growth_YoY": return "📈 Рост BVPS (год к году)";
            case "BVPS_Growth_3Y_CAGR": return "📈 Рост BVPS (3 года CAGR)";
            case "BVPS_Growth_5Y_CAGR": return "📈 Рост BVPS (5 лет CAGR)";
            case "Sales_Per_Share_Growth_YoY": return "📈 Рост Sales/Share (год к году)";
            case "Sales_Per_Share_Growth_3Y_CAGR": return "📈 Рост Sales/Share (3 года CAGR)";
            case "Sales_Per_Share_Growth_5Y_CAGR": return "📈 Рост Sales/Share (5 лет CAGR)";

            // ============ ДИВИДЕНДЫ ============
            case "DPS": return "💵 DPS (Дивиденд на акцию)";
            case "DPR_Payout_Ratio": return "💸 Коэффициент выплат (DPR)";
            case "Retention_Ratio": return "💰 Коэффициент реинвестирования";
            case "Sustainable_Growth_Rate": return "📈 Устойчивый темп роста";
            case "Dividend_Coverage_Ratio": return "📊 Коэффициент покрытия дивидендов";
            case "Dividend_Yield": return "💸 Дивидендная доходность";

            // ============ ДОХОДНОСТЬ ДЛЯ АКЦИОНЕРОВ ============
            case "FCF_Yield": return "💵 FCF Yield";
            case "Earnings_Yield": return "💰 Earnings Yield (1/P/E)";
            case "Buyback_Yield": return "🔄 Buyback Yield (Обратный выкуп)";
            case "Total_Shareholder_Yield": return "💰 Total Shareholder Yield";

            // ============ ОЦЕНОЧНЫЕ МОДЕЛИ ============
            case "Graham_Number": return "📘 Число Грэма";
            case "Ben_Graham_Formula": return "📘 Формула Бена Грэма";
            case "Peter_Lynch_Fair_Value": return "📘 Справедливая цена (Питер Линч)";
            case "Altman_Z_Score": return "📊 Альтман Z-Score (риск банкротства)";
            case "Piotroski_F_Score": return "📊 Пиотроски F-Score (сила компании)";

            // ============ РЫНОЧНЫЕ РИСКИ ============
            case "Beta": return "📉 Бета-коэффициент (рыночный риск)";
            case "Alpha": return "📈 Альфа (доходность выше рынка)";
            case "R_Squared": return "📊 R-квадрат (корреляция с рынком)";
            case "Sharpe_Ratio": return "📊 Коэффициент Шарпа";
            case "Treynor_Ratio": return "📊 Коэффициент Трейнора";
            case "Sortino_Ratio": return "📊 Коэффициент Сортино";
            case "Standard_Deviation": return "📊 Стандартное отклонение";
            case "Value_at_Risk_VaR": return "⚠️ VaR (Риск потерь)";
            case "Maximum_Drawdown": return "📉 Максимальная просадка";

            default: return columnName.replace("_", " ");
        }
    }
    private void showMetricsInCategory(String categoryKey, List<StateAdapter.Metric> metrics) {
        Integer recyclerViewId = recyclerViewIds.get(categoryKey);
        if (recyclerViewId == null) return;

        RecyclerView recyclerView = findViewById(recyclerViewId);
        if (metrics.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            return;
        }

        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StateAdapter adapter = new StateAdapter(this, metrics);
        recyclerView.setAdapter(adapter);
    }
}